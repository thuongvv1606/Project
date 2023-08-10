using Assignment_PRN211.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using PagedList;

namespace Assignment_PRN211.Controllers
{
    public class RoomController : Controller
    {
        private readonly Assignment_PRN211Context _context;
        private readonly IHttpContextAccessor _httpContext;

        public RoomController(Assignment_PRN211Context context, IHttpContextAccessor httpContext)
        {
            _context = context;
            _httpContext = httpContext;
        }

        public async Task<IActionResult> List(string? searchStr, string? gender, int? pageNumber)
        {
            ViewBag.Current = "Room";
            List<Room> rooms = _context.Rooms.ToList();
            if (pageNumber == null) pageNumber = 1;
            int page = (pageNumber ?? 1);
            if (_httpContext.HttpContext.Session.GetString("user") != null &&
                JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                rooms = _context.Rooms.Where(p => p.Gender ==
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
            ViewBag.count = rooms.Count;
            ViewBag.page = page;
            return View(rooms.ToPagedList(page, 10));
        }

        public async Task<IActionResult> Detail(string id, string status)
        {
            ViewBag.Current = "Room";
            ViewBag.status = status;
            User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
            if (id == null || _context.Rooms == null)
            {
                return NotFound();
            }
            List<Bed> beds = _context.Beds.Where(p => p.RoomId == id).ToList();
            foreach (Bed b in beds)
            {
                var order = _context.Orders.SingleOrDefault(p => p.BedId == b.BedId && p.RoomId == b.RoomId && p.SemesterId == "Su23");
                if (order != null)
                {
                    b.Orders.Add(order);
                }
            }
            if (status == "true")
            {
                beds = beds.Where(p => p.Orders.Count != 0).ToList();
            }
            else if (status == "false")
            {
                beds = beds.Where(p => p.Orders.Count == 0).ToList();
            }
            var room = await _context.Rooms
                .Include(r => r.Category)
                .Include(r => r.Dom)
                .FirstOrDefaultAsync(m => m.RoomId == id);
            ViewBag.id = id;
            ViewBag.room = room;
            var o = _context.Orders.SingleOrDefault(p => p.UserId == u.UserId && p.SemesterId == "Su23");
            ViewBag.o = o;
            ViewBag.count = beds.Count;
            if (room == null)
            {
                return NotFound();
            }
            return View(beds);
        }

        public IActionResult Add()
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                ViewData["Dom"] = new SelectList(_context.Dormitories, "DomId", "DomName");
                ViewData["CategoryId"] = new SelectList(_context.RoomCategories, "CategoryId", "CategoryName");
                return View();
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Add([Bind("RoomId,DomId,CategoryId,Gender")] Room room, string gender)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                Dormitory dom = _context.Dormitories.SingleOrDefault(p => p.DomId == room.DomId);
                RoomCategory category = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                Room r = _context.Rooms.SingleOrDefault(p => p.RoomId == room.RoomId);
                string num = category.CategoryName.Split(" ")[1];
                if (r != null)
                {
                    ViewBag.error = "Mã phòng này đã tồn tại.";
                }
                else if (!room.RoomId.StartsWith(dom.DomName))
                {
                    ViewBag.error = "Mã phòng phải bắt đầu bằng chữ cái tên tòa.";
                }
                else if (gender == null)
                {
                    ViewBag.error = "Trường giới tính không được bỏ qua.";
                }
                else
                {
                    room.RoomId = room.RoomId.ToUpper();
                    room.Gender = Boolean.Parse(gender);
                    _context.Rooms.Add(room);
                    for (int i = 0; i < int.Parse(num); i++)
                    {
                        Bed bed = new Bed();
                        bed.BedId = (i + 1);
                        bed.RoomId = room.RoomId;
                        _context.Beds.Add(bed);
                    }
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(List));
                }
                ViewData["Dom"] = new SelectList(_context.Dormitories, "DomId", "DomName");
                ViewData["CategoryId"] = new SelectList(_context.RoomCategories, "CategoryId", "CategoryName", room.CategoryId);
                return View(room);
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        public async Task<IActionResult> Delete(string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                if (id == null || _context.Rooms == null)
                {
                    return NotFound();
                }

                var room = await _context.Rooms
                    .Include(r => r.Category)
                    .Include(r => r.Dom)
                    .FirstOrDefaultAsync(m => m.RoomId == id);
                if (room == null)
                {
                    return NotFound();
                }

                return View(room);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Current = "Room";
                if (_context.Rooms == null)
                {
                    return Problem("Entity set 'Assignment_PRN211Context.Rooms'  is null.");
                }
                var room = await _context.Rooms.FindAsync(id);
                var bed = _context.Beds.Where(p => p.RoomId == id).ToList();
                var order = _context.Orders.Where(p => p.RoomId == id).ToList();
                var changes = new List<ChangeRoomRequest>();
                var requests = new List<RequestBooking>();
                foreach (var b in bed)
                {
                    var change = _context.ChangeRoomRequests.Where(p => p.BedId1 == b.BedId || p.BedId2 == b.BedId).ToList();
                    var request = _context.RequestBookings.Where(p => p.BedId == b.BedId).ToList();
                    changes.AddRange(change);
                    requests.AddRange(requests);
                }
                if (room != null)
                {
                    foreach (RequestBooking r in requests)
                    {
                        _context.RequestBookings.Remove(r);
                    }
                    foreach (ChangeRoomRequest r in changes)
                    {
                        _context.ChangeRoomRequests.Remove(r);
                    }
                    foreach (Order o in order)
                    {
                        _context.Orders.Remove(o);
                    }
                    foreach (Bed b in bed)
                    {
                        _context.Beds.Remove(b);
                    }
                    _context.Rooms.Remove(room);
                }
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(List));
            }
            else
            {
                return View("Error_Authourized");
            }

        }

        private bool RoomExists(string id)
        {
            return (_context.Rooms?.Any(e => e.RoomId == id)).GetValueOrDefault();
        }
    }
}
