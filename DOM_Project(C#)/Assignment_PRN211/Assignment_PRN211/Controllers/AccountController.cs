using Assignment_PRN211.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using PagedList;

namespace Assignment_PRN211.Controllers
{
    public class AccountController : Controller
    {
        private readonly Assignment_PRN211Context _context;
        private readonly IHttpContextAccessor _httpContext;

        public AccountController(Assignment_PRN211Context context, IHttpContextAccessor httpContext)
        {
            _context = context;
            _httpContext = httpContext;
        }

        public IActionResult Index()
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null)
            {
                ViewBag.Cur = "Profile";
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                if (u != null)
                {
                    var o = _context.Orders.Where(p => p.UserId == u.UserId).ToList();
                    u.Orders = o;
                    var request = _context.RequestBookings.Where(p => p.UserId == u.UserId).ToList();
                    u.RequestBookings = request;
                    var fee = _context.Fees.SingleOrDefault(p => p.UserId == u.UserId);
                    u.Fee = fee;
                }
                return View(u);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        public async Task<IActionResult> EditProfile(string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null)
            {
                ViewBag.Cur = "Profile";
                if (id == null || _context.Users == null)
                {
                    return NotFound();
                }

                var user = await _context.Users.FindAsync(id);
                if (user == null)
                {
                    return NotFound();
                }
                return View(user);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> EditProfile(string id, [Bind("UserName,UserId,UserDob,UserGender,Password,IsAdmin,IsActive")] User user, string gender)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null)
            {
                ViewBag.Cur = "Profile";
                if (id != user.UserId)
                {
                    return NotFound();
                }
                if (ModelState.IsValid)
                {
                    try
                    {
                        user.UserGender = Boolean.Parse(gender);
                        _context.Update(user);
                        await _context.SaveChangesAsync();
                        _httpContext.HttpContext.Session.SetString("user", JsonConvert.SerializeObject(user));
                    }
                    catch (DbUpdateConcurrencyException)
                    {
                        if (!UserExists(user.UserId))
                        {
                            return NotFound();
                        }
                        else
                        {
                            throw;
                        }
                    }
                    return RedirectToAction(nameof(Index));
                }
                return View(user);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        private bool UserExists(string id)
        {
            return (_context.Users?.Any(e => e.UserId == id)).GetValueOrDefault();
        }
        public async Task<IActionResult> History()
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Cur = "History";
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                if (u != null)
                {
                    var o = _context.Orders.Where(p => p.UserId == u.UserId).ToList();
                    foreach (Order or in o)
                    {
                        var semester = _context.Semesters.SingleOrDefault(p => p.SemesterId == or.SemesterId);
                        or.Semester = semester;
                    }
                    u.Orders = o;
                }
                return View(u);
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        public async Task<IActionResult> Money(string money)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Cur = "Money";
                ViewBag.success = "";
                ViewBag.money = money;
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                if (u != null)
                {
                    var o = _context.Fees.SingleOrDefault(p => p.UserId == u.UserId);
                    u.Fee = o;
                    if (money != null)
                    {
                        o.Fee1 = o.Fee1 + decimal.Parse(money);
                        u.Fee = o;
                        _context.Fees.Update(o);
                        await _context.SaveChangesAsync();
                        ViewBag.success = "Nạp tiền thành công!";
                    }
                }
                return View(u);
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        public async Task<IActionResult> ChangePass()
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null)
            {
                ViewBag.Cur = "Password";
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                return View(u);
            }

            else
            {
                return View("Error_Authourized");
            }
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> ChangePass(string pass, string newpass, string repass, [Bind("UserName,UserId,UserDob,UserGender,Password,IsAdmin")] User user)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null)
            {
                ViewBag.Cur = "Password";
                ViewBag.success = "";
                ViewBag.fail = "";
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                if (pass != null && newpass != null && repass != null)
                {
                    if (!pass.Equals(u.Password))
                    {
                        ViewBag.fail = "Mật khẩu cũ không đúng. Người dùng vui lòng nhập lại.";
                    }
                    else if (!repass.Equals(newpass))
                    {
                        ViewBag.fail = "Mật khẩu nhập lại phải khớp mật khẩu mới vừa nhập phía trên. Người dùng vui lòng nhập lại.";
                    }
                    else
                    {
                        user.Password = newpass;
                        _context.Users.Update(user);
                        await _context.SaveChangesAsync();
                        _httpContext.HttpContext.Session.SetString("user", JsonConvert.SerializeObject(user));
                        ViewBag.success = "Đổi mật khẩu thành công";
                    }
                }
                return View(u);
            }

            else
            {
                return View("Error_Authourized");
            }
        }

        public async Task<IActionResult> List(string? searchStr, string? gender, int? pageNumber, string act, string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Cur = "Account";
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                if (act == "activate")
                {
                    User uid = _context.Users.SingleOrDefault(p => p.UserId == id);
                    uid.IsActive = true;
                    _context.Users.Update(uid);
                    _context.SaveChanges();
                }
                else if (act == "deactivate")
                {
                    User uid = _context.Users.SingleOrDefault(p => p.UserId == id);
                    uid.IsActive = false;
                    _context.Users.Update(uid);
                    Fee fee = _context.Fees.SingleOrDefault(p => p.UserId == id);
                    Order o = _context.Orders.SingleOrDefault(p => p.UserId == id && p.SemesterId == "Su23");
                    decimal? price1 = 0;
                    decimal? price2 = 0;
                    if (o != null)
                    {
                        Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == o.RoomId);
                        RoomCategory cate = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                        price1 = cate.Price;
                        _context.Orders.Remove(o);
                    }
                    RequestBooking req = _context.RequestBookings.SingleOrDefault(p => p.UserId == id);
                    if (req != null)
                    {
                        Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == req.RoomId);
                        RoomCategory cate = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                        price2 = cate.Price * 4;
                        _context.RequestBookings.Remove(req);
                    }
                    ChangeRoomRequest ch = _context.ChangeRoomRequests.SingleOrDefault(p => p.UserId1 == id);
                    if (ch != null)
                    {
                        Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == ch.RoomId2);
                        RoomCategory cate = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                        price2 = cate.Price * 4;
                        _context.ChangeRoomRequests.Remove(ch);
                    }
                    ChangeRoomRequest ch1 = _context.ChangeRoomRequests.SingleOrDefault(p => p.UserId2 == id);
                    if (ch1 != null)
                    {
                        RequestBooking r = new RequestBooking();
                        r.UserId = ch1.UserId1;
                        r.RoomId = ch1.RoomId2;
                        r.BedId = (int)ch1.BedId2;
                        _context.RequestBookings.Add(r);
                        _context.ChangeRoomRequests.Remove(ch1);
                    }
                    if (price1 < price2)
                    {
                        fee.Fee1 = fee.Fee1 + price2;
                    }
                    else
                    {
                        fee.Fee1 = fee.Fee1 + price1;
                    }
                    _context.Fees.Update(fee);
                    _context.SaveChanges();
                }
                List<User> users = _context.Users.Where(p => p.IsAdmin == false).ToList();
                if (pageNumber == null) pageNumber = 1;
                int page = (pageNumber ?? 1);


                ViewBag.search = searchStr;
                ViewBag.gender = gender;
                if (gender != null)
                {
                    users = users.Where(p => p.UserGender == Boolean.Parse(gender)).ToList();
                }
                if (searchStr != null)
                {
                    users = users.Where(p => p.UserId.Contains(searchStr) || p.UserName.Contains(searchStr)).ToList();
                }
                ViewBag.count = users.Count;
                ViewBag.page = page;
                return View(users.ToPagedList(page, 10));
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        public async Task<IActionResult> Details(string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null)
            {
                ViewBag.Cur = "Account";
                if (id == null || _context.Users == null)
                {
                    return NotFound();
                }

                var user = await _context.Users
                    .FirstOrDefaultAsync(m => m.UserId == id);
                if (user == null)
                {
                    return NotFound();
                }

                return View(user);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        public IActionResult Add()
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Cur = "Account";
                return View();
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Add([Bind("UserName,UserId,UserDob,UserGender,Password,IsAdmin")] User user)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Cur = "Account";
                if (ModelState.IsValid)
                {
                    _context.Add(user);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
                return View(user);
            }
            else
            {
                return View("Error_Authourized.cshtml");
            }
        }

        public async Task<IActionResult> Edit(string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null)
            {
                ViewBag.Cur = "Account";
                if (id == null || _context.Users == null)
                {
                    return NotFound();
                }

                var user = await _context.Users.FindAsync(id);
                if (user == null)
                {
                    return NotFound();
                }
                return View(user);
            }
            else
            {
                return View("Error_Authourized.cshtml");
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(string id, string gender, string active, [Bind("UserName,UserId,UserDob,UserGender,Password,IsAdmin,IsActive")] User user)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null)
            {
                ViewBag.Cur = "Account";
                if (id != user.UserId)
                {
                    return NotFound();
                }

                if (ModelState.IsValid)
                {
                    try
                    {
                        user.UserGender = Boolean.Parse(gender);
                        user.IsActive = Boolean.Parse(active);
                        _context.Update(user);
                        await _context.SaveChangesAsync();
                        _httpContext.HttpContext.Session.SetString("user", JsonConvert.SerializeObject(user));
                    }
                    catch (DbUpdateConcurrencyException)
                    {
                        if (!UserExists(user.UserId))
                        {
                            return NotFound();
                        }
                        else
                        {
                            throw;
                        }
                    }
                    return RedirectToAction(nameof(List));
                }
                return View(user);
            }
            else
            {
                return View("Error_Authourized.cshtml");
            }
        }

    }


}
