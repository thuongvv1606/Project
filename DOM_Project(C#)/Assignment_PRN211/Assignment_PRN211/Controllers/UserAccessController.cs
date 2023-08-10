using Assignment_PRN211.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using System.Text.Json.Serialization;
using Newtonsoft.Json;
using System.Diagnostics.Metrics;

namespace Assignment_PRN211.Controllers
{
    public class UserAccessController : Controller
    {
        private readonly Assignment_PRN211Context _context;
        private readonly IHttpContextAccessor _httpContext;

        public UserAccessController(Assignment_PRN211Context context, IHttpContextAccessor httpContext)
        {
            _context = context;
            _httpContext = httpContext;
        }

        public IActionResult Login(string userid, string password)
        {
            ViewBag.Current = "Login";
			ViewBag.userid = userid;
			ViewBag.password = password;
			if (userid == null && password == null)
            {
                return View();
            }
            User u = _context.Users.FirstOrDefault(u => u.UserId.Equals(userid) && u.Password.Equals(password));
            if (u != null)
            {
                if (u.IsActive == false)
                {
                    ViewBag.error = "Tài khoản này đã bị vô hiệu hóa.";
					return View(u);
				}
                _httpContext.HttpContext.Session.SetString("user", JsonConvert.SerializeObject(u));
                return RedirectToAction("Index", "Home");
            }
            else
            {
                ViewBag.error = "Sai tài khoản hoặc mật khẩu. Người dùng vui lòng nhập lại.";
                return View(u);
            }
        }

        public IActionResult Register()
        {
            ViewBag.Current = "Register";
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Register([Bind("UserName, UserId, UserDob, UserGender, Password, IsAdmin, IsActive")] User user, string gender, string repassword)
        {
            ViewBag.Current = "Register";
            if (!repassword.Equals(user.Password))
            {
                ViewBag.error = "Mật khẩu nhập lại phải khớp mật khẩu phía trên. Người dùng vui lòng nhập lại.";
            } else if (gender == null)
            {
				ViewBag.error = "Người dùng bắt buộc phải chọn giới tính.";
			}
            else
            {
                User u = _context.Users.SingleOrDefault(m => m.UserId == user.UserId);
                if (u == null)
                {
					user.IsAdmin = false;
                    user.IsActive = true;
					user.UserGender = Boolean.Parse(gender);
                    _context.Users.Add(user);
                    await _context.SaveChangesAsync();
                    return RedirectToAction("Login", "UserAccess");
                }
                else
                {
                    ViewBag.error = "ID người dùng này đã tồn tại.";
                }
            }
            return View(user);
        }

        public IActionResult Logout()
        {
            _httpContext.HttpContext.Session.Remove("user");
            return RedirectToAction("Index", "Home");
        }
    }
}
