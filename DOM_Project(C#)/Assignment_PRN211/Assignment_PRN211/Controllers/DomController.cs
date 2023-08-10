using Assignment_PRN211.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using PagedList;

namespace Assignment_PRN211.Controllers
{
    public class DomController : Controller
    {
        private readonly Assignment_PRN211Context _context;
        private readonly IHttpContextAccessor _httpContext;

        public DomController(Assignment_PRN211Context context, IHttpContextAccessor httpContext)
        {
            _context = context;
            _httpContext = httpContext;
        }

        public IActionResult Index()
        {
            ViewBag.Current = "Room";
            List<Dormitory> dormitories = _context.Dormitories.ToList();
            return View(dormitories);
        }

        public async Task<IActionResult> Detail(int? id, string? searchStr, string? gender, int? pageNumber)
        {
            ViewBag.Current = "Room";
            ViewBag.search = searchStr;
            ViewBag.dom = _context.Dormitories.FirstOrDefault(p => p.DomId == id);
            if (pageNumber == null) pageNumber = 1;
            int page = (pageNumber ?? 1);
            List<Room> rooms = _context.Rooms.Where(p => p.DomId == id).ToList();
            if (_httpContext.HttpContext.Session.GetString("user") != null &&
                JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                rooms = _context.Rooms.Where(p => p.DomId == id && p.Gender ==
                JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).UserGender).ToList();
            }
            foreach (Room r in rooms)
            {
                var category = _context.RoomCategories.FirstOrDefault(p => p.CategoryId == r.CategoryId);
                var dormitory = _context.Dormitories.FirstOrDefault(p => p.DomId == r.DomId);
                r.Category = category;
                r.Dom = dormitory;
            }
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
            ViewBag.count = _context.Dormitories.ToList().Count;
            return View();
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Add([Bind("DomId,DomName")] Dormitory dormitory)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                ViewBag.count = _context.Dormitories.ToList().Count;
                Dormitory dom = _context.Dormitories.SingleOrDefault(p => p.DomName == dormitory.DomName);
                if (dom != null)
                {
                    ViewBag.error = "Tòa kí túc xá này đã tồn tại. Vui lòng nhập lại tên.";
                }
                else
                {
                    dormitory.DomName = dormitory.DomName.ToUpper();
                    _context.Dormitories.Add(dormitory);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
                return View(dormitory);
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
                if (id == null || _context.Dormitories == null)
                {
                    return NotFound();
                }

                var dormitory = await _context.Dormitories
                    .FirstOrDefaultAsync(m => m.DomId == id);
                if (dormitory == null)
                {
                    return NotFound();
                }

                return View(dormitory);
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
            if (_httpContext.HttpContext.Session.GetString("user") != null || JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Current = "Room";
                if (_context.Dormitories == null)
                {
                    return Problem("Entity set 'Assignment_PRN211Context.Dormitories'  is null.");
                }
                var dormitory = await _context.Dormitories.FindAsync(id);
                var room = _context.Rooms.Where(p => p.DomId == id).ToList();
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
                if (dormitory != null)
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
                    _context.Dormitories.Remove(dormitory);
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
            if (_httpContext.HttpContext.Session.GetString("user") != null || JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Current = "Room";
                if (id == null || _context.Dormitories == null)
                {
                    return NotFound();
                }

                var dormitory = await _context.Dormitories.FindAsync(id);
                if (dormitory == null)
                {
                    return NotFound();
                }
                return View(dormitory);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("DomId,DomName")] Dormitory dormitory)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null || JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Current = "Room";
                if (id != dormitory.DomId)
                {
                    return NotFound();
                }
                Dormitory dom = _context.Dormitories.SingleOrDefault(p => p.DomName == dormitory.DomName && p.DomId != dormitory.DomId);
                if (dom != null)
                {
                    ViewBag.error = "Tòa kí túc xá này đã tồn tại.";
                    return View(dormitory);
                }
                else
                {
                    dormitory.DomName = dormitory.DomName.ToUpper();
                    _context.Update(dormitory);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        private bool DormitoryExists(int id)
        {
            return (_context.Dormitories?.Any(e => e.DomId == id)).GetValueOrDefault();
        }
    }
}
