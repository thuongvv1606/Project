using Assignment_PRN211.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.CodeAnalysis.CSharp.Syntax;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using PagedList;
using System.Drawing.Printing;

namespace Assignment_PRN211.Controllers
{
    public class CategoryController : Controller
    {
        private readonly Assignment_PRN211Context _context;
        private readonly IHttpContextAccessor _httpContext;

        public CategoryController(Assignment_PRN211Context context, IHttpContextAccessor httpContext)
        {
            _context = context;
            _httpContext = httpContext;
        }

        public IActionResult Index()
        {
            
                ViewBag.Current = "Room";
                List<RoomCategory> roomCategories = _context.RoomCategories.ToList();
                return View(roomCategories);
            
        }

        public async Task<IActionResult> Detail(int id, string searchStr, string gender, int? pageNumber)
        {
            
                ViewBag.Current = "Room";
                ViewBag.category = _context.RoomCategories.FirstOrDefault(p => p.CategoryId == id);
                List<Room> rooms = _context.Rooms.Where(p => p.CategoryId == id).ToList();
                if (pageNumber == null) pageNumber = 1;
                int page = (pageNumber ?? 1);
                if (_httpContext.HttpContext.Session.GetString("user") != null &&
                    JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
                {
                    rooms = _context.Rooms.Where(p => p.CategoryId == id && p.Gender ==
                    JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).UserGender).ToList();
                }
                foreach (Room r in rooms)
                {
                    var category = _context.RoomCategories.FirstOrDefault(p => p.CategoryId == r.CategoryId);
                    var dormitory = _context.Dormitories.FirstOrDefault(p => p.DomId == r.DomId);
                    r.Category = category;
                    r.Dom = dormitory;
                }
                ViewBag.search = searchStr;
                ViewBag.gender = gender;
                if (gender != null && gender != "0")
                {
                    rooms = rooms.Where(p => p.Gender == Boolean.Parse(gender)).ToList();
                }
                if (searchStr != null)
                {
                    rooms = rooms.Where(p => p.RoomId.ToLower().Contains(searchStr.ToLower()) || p.Category.CategoryName.ToLower().Contains(searchStr.ToLower())).ToList();
                }
                ViewBag.id = id;
                ViewBag.count = rooms.Count;
                ViewBag.page = page;
                return View(rooms.ToPagedList(page, 10));
            
        }

        public IActionResult Add()
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                ViewBag.count = _context.RoomCategories.ToList().Count;
                return View();
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Add([Bind("CategoryId,CategoryName,Price")] RoomCategory roomCategory)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                ViewBag.count = _context.RoomCategories.ToList().Count;
                RoomCategory roomcate = _context.RoomCategories.FirstOrDefault(p => p.CategoryName == roomCategory.CategoryName);
                if (roomcate != null)
                {
                    ViewBag.error = "Loại phòng này đã tồn tại. Vui lòng nhập lại tên.";
                }
                else
                {
                    _context.Add(roomCategory);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
                return View(roomCategory);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        public async Task<IActionResult> Delete(int? id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                if (id == null || _context.RoomCategories == null)
                {
                    return NotFound();
                }

                var cate = await _context.RoomCategories
                    .FirstOrDefaultAsync(m => m.CategoryId == id);
                if (cate == null)
                {
                    return NotFound();
                }

                return View(cate);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                if (_context.RoomCategories == null)
                {
                    return Problem("Entity set 'Assignment_PRN211Context.RoomCategories'  is null.");
                }
                var cate = await _context.RoomCategories.FindAsync(id);
                var room = _context.Rooms.Where(p => p.CategoryId == id).ToList();
                var beds = new List<Bed>();
                var orders = new List<Order>();
                foreach (Room r in room)
                {
                    var bed = _context.Beds.Where(p => p.RoomId == r.RoomId).ToList();
                    var order = _context.Orders.Where(p => p.RoomId == r.RoomId).ToList();
                    beds.AddRange(bed);
                    orders.AddRange(order);
                }
                var changes = new List<ChangeRoomRequest>();
                var requests = new List<RequestBooking>();
                foreach (var b in beds)
                {
                    var change = _context.ChangeRoomRequests.Where(p => p.BedId1 == b.BedId || p.BedId2 == b.BedId).ToList();
                    var request = _context.RequestBookings.Where(p => p.BedId == b.BedId).ToList();
                    changes.AddRange(change);
                    requests.AddRange(requests);
                }
                if (cate != null)
                {
                    foreach (RequestBooking r in requests)
                    {
                        _context.RequestBookings.Remove(r);
                    }
                    foreach (ChangeRoomRequest r in changes)
                    {
                        _context.ChangeRoomRequests.Remove(r);
                    }
                    foreach (Order o in orders)
                    {
                        _context.Orders.Remove(o);
                    }
                    foreach (Bed b in beds)
                    {
                        _context.Beds.Remove(b);
                    }
                    foreach (Room r in room)
                    {
                        _context.Rooms.Remove(r);
                    }
                    _context.RoomCategories.Remove(cate);
                }
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        public async Task<IActionResult> Edit(int? id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                if (id == null || _context.RoomCategories == null)
                {
                    return NotFound();
                }

                var roomCategory = await _context.RoomCategories.FindAsync(id);
                if (roomCategory == null)
                {
                    return NotFound();
                }
                return View(roomCategory);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("CategoryId,CategoryName,Price")] RoomCategory roomCategory)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                if (id != roomCategory.CategoryId)
                {
                    return NotFound();
                }
                RoomCategory cate = _context.RoomCategories.SingleOrDefault(p => p.CategoryName == roomCategory.CategoryName && p.CategoryId != roomCategory.CategoryId);
                if (cate != null)
                {
                    ViewBag.error = "Loại phòng kí túc xá này đã tồn tại.";
                    return View(roomCategory);
                }
                else
                {
                    _context.RoomCategories.Update(roomCategory);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        private bool RoomCategoryExists(int id)
        {
            return (_context.RoomCategories?.Any(e => e.CategoryId == id)).GetValueOrDefault();
        }
    }
}
