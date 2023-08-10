using Assignment_PRN211.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using PagedList;
using System.Security.Cryptography;

namespace Assignment_PRN211.Controllers
{
    public class BookingController : Controller
    {
        private readonly Assignment_PRN211Context _context;
        private readonly IHttpContextAccessor _httpContext;

        public BookingController(Assignment_PRN211Context context, IHttpContextAccessor httpContext)
        {
            _context = context;
            _httpContext = httpContext;
        }
        public IActionResult Index(string search, int? pageNumber, string check, string oid, string cid)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Current = "Booking";
                ViewBag.O = _context.Orders.Where(p => p.SemesterId == "Su23").ToList();
                ViewBag.search = search;
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                if (oid != null)
                {
                    RequestBooking o = _context.RequestBookings.SingleOrDefault(p => p.UserId == u.UserId);
                    _context.RequestBookings.Remove(o);
                    Fee f = _context.Fees.SingleOrDefault(p => p.UserId == u.UserId);
                    decimal? fee = decimal.Parse(oid) + f.Fee1;
                    f.Fee1 = fee;
                    _context.Fees.Update(f);
                    _context.SaveChanges();
                }
                if (cid != null)
                {
                    ChangeRoomRequest c = _context.ChangeRoomRequests.SingleOrDefault(p => p.UserId1 == u.UserId);
                    Room room2 = _context.Rooms.SingleOrDefault(p => p.RoomId == c.RoomId2);
                    RoomCategory cate2 = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room2.CategoryId);
                    Fee f = _context.Fees.SingleOrDefault(p => p.UserId == u.UserId);
                    decimal? fee = 0;
                    if (c.RoomId1 != null)
                    {
                        Room room1 = _context.Rooms.SingleOrDefault(p => p.RoomId == c.RoomId1);
                        RoomCategory cate1 = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room1.CategoryId);
                        fee = f.Fee1 + cate2.Price * 4 - cate1.Price * 4;
                    }
                    _context.ChangeRoomRequests.Remove(c);
                    fee =  f.Fee1 + cate2.Price * 4;
                    f.Fee1 = fee;
                    _context.Fees.Update(f);
                    _context.SaveChanges();
                }
                var list = _context.Rooms.Where(p => p.Gender == u.UserGender).ToList();
                var request = _context.RequestBookings.FirstOrDefault(p => p.UserId == u.UserId);
                if (request != null)
                {
                    Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == request.RoomId);
                    RoomCategory category = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                    ViewBag.price = category.Price;
                }
                var order = _context.Orders.SingleOrDefault(p => p.UserId == u.UserId && p.SemesterId == "Su23");
                if (order != null)
                {
                    Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == order.RoomId);
                    RoomCategory category = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                    ViewBag.price = category.Price;
                }
                foreach (var room in list)
                {
                    var category = _context.RoomCategories.FirstOrDefault(p => p.CategoryId == room.CategoryId);
                    var dorm = _context.Dormitories.FirstOrDefault(p => p.DomId == room.DomId);
                    room.Category = category;
                    room.Dom = dorm;
                }
                var change = _context.ChangeRoomRequests.SingleOrDefault(p => p.UserId1 == u.UserId);
                var changeO = _context.ChangeRoomRequests.Where(p => p.UserId2 == u.UserId).ToList().Count;
                ViewBag.change = change;
                if (pageNumber == null) pageNumber = 1;
                int page = (pageNumber ?? 1);
                if (search != null)
                {
                    list = list.Where(p => p.Category.CategoryName.ToLower().Contains(search.ToLower()) ||
                    p.Dom.DomName.ToLower().Contains(search.ToLower()) || p.RoomId.ToLower().Contains(search.ToLower())).ToList();
                }
                if (check == null && order == null)
                {
                    Order order2 = _context.Orders.SingleOrDefault(p => p.UserId == u.UserId && p.SemesterId == "S23");
                    if (order2 != null)
                    {
                        Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == order2.RoomId);
                        RoomCategory category = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                        ViewBag.price = category.Price;
                        ViewBag.oldorder = order2;
                    }
                }
                ViewBag.count = list.Count;
                ViewBag.page = page;
                ViewBag.request = request;
                ViewBag.order = order;
                ViewBag.countChange = changeO;
                return View(list.ToPagedList(page, 10));
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        public async Task<IActionResult> Order(string id, string roomid)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Current = "Booking";
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                Bed beds = _context.Beds.SingleOrDefault(p => p.RoomId.Equals(roomid) && p.BedId == int.Parse(id));
                Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == roomid);
                RoomCategory cate = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                Order order = _context.Orders.SingleOrDefault(p => p.UserId == u.UserId && p.SemesterId == "Su23");
                decimal? price = 0;
                if (order != null)
                {
                    Room room1 = _context.Rooms.SingleOrDefault(p => p.RoomId == order.RoomId);
                    RoomCategory cate1 = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room1.CategoryId);
                    if (cate1 != null)
                    {
                        price = cate1.Price;
                    }
                }
                ViewBag.cate = cate;
                ViewBag.room = room;
                ViewBag.roomid = roomid;
                ViewBag.id = id;
                Fee fee = _context.Fees.SingleOrDefault(p => p.UserId == u.UserId);
                ViewBag.Fee = fee;
                u.Fee = fee;
                if ((fee.Fee1 + price * 4) < (cate.Price * 4))
                {
                    ViewBag.error = $"Số dư tài khoản của bạn thiếu {(int)(cate.Price * 4 - price * 4 - fee.Fee1)}đ để đặt phòng.";
                }
                return View();
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Order(string id, string roomid, [Bind("UserId,RoomId,BedId")] RequestBooking requestBooking)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Current = "Booking";
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                Bed beds = _context.Beds.SingleOrDefault(p => p.RoomId.Equals(roomid) && p.BedId == int.Parse(id));
                Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == roomid);
                RoomCategory cate = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                Order order = _context.Orders.SingleOrDefault(p => p.UserId == u.UserId && p.SemesterId == "Su23");
                decimal? price = 0;
                if (order != null)
                {
                    Room room1 = _context.Rooms.SingleOrDefault(p => p.RoomId == order.RoomId);
                    RoomCategory cate1 = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room1.CategoryId);
                    if (cate1 != null)
                    {
                        price = cate1.Price;
                    }
                }
                ViewBag.cate = cate;
                ViewBag.room = room;
                ViewBag.roomid = roomid;
                ViewBag.id = id;
                Fee fee = _context.Fees.SingleOrDefault(p => p.UserId == u.UserId);
                ViewBag.Fee = fee;
                u.Fee = fee;
                if ((fee.Fee1 + price * 4) < (cate.Price * 4))
                {
                    ViewBag.error = $"Số dư tài khoản của bạn thiếu {(int)(cate.Price * 4 - price * 4 - fee.Fee1)}đ để đặt phòng.";
                    return View(requestBooking);
                }
                else
                {
                    requestBooking.RoomId = roomid;
                    requestBooking.BedId = int.Parse(id);
                    fee.Fee1 = fee.Fee1 + (price * 4) - (cate.Price * 4);
                    u.Fee = fee;
                    _context.RequestBookings.Add(requestBooking);
                    _context.Fees.Update(fee);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        public async Task<IActionResult> Request()
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Cur = "Booking_Admin";
                var request = _context.RequestBookings.ToList();
                return View(request);
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Request(string act, string sid, string rid, string bid)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Cur = "Booking_Admin";
                if (act == "add")
                {
                    Order order = _context.Orders.SingleOrDefault(p => p.UserId == sid && p.SemesterId == "Su23");
                    if (order != null)
                    {
                        _context.Orders.Remove(order);
                    }
                    Order o = new Order();
                    o.SemesterId = "Su23";
                    o.UserId = sid;
                    o.RoomId = rid;
                    o.BedId = int.Parse(bid);
                    _context.Orders.Add(o);
                    RequestBooking req = new RequestBooking();
                    req.UserId = sid;
                    req.RoomId = rid;
                    req.BedId = int.Parse(bid);
                    _context.RequestBookings.Remove(req);
                    await _context.SaveChangesAsync();
                }
                else if (act == "delete")
                {
                    RequestBooking req = new RequestBooking();
                    req.UserId = sid;
                    req.RoomId = rid;
                    req.BedId = int.Parse(bid);
                    _context.RequestBookings.Remove(req);
                    Fee fee = _context.Fees.SingleOrDefault(p => p.UserId == sid);
                    Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == rid);
                    RoomCategory cate = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                    fee.Fee1 += (cate.Price * 4);
                    _context.Fees.Update(fee);
                    await _context.SaveChangesAsync();
                }
                var request = _context.RequestBookings.ToList();
                return View(request);
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        public async Task<IActionResult> ChangeRoom()
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Cur = "ChangeRoom";
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                var request = await _context.ChangeRoomRequests.AsNoTracking().Where(p => p.UserId2 == u.UserId).ToListAsync();
                _httpContext.HttpContext.Session.SetString("user", JsonConvert.SerializeObject(u));
                return View(request);
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> ChangeRoom(string act, string uid, string sid, string rid1, string bid1, string rid2, string bid2)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Cur = "ChangeRoom";
                if (sid != null && rid1 != null && bid1 != null && rid2 != null && bid2 != null)
                {
                    Room room1 = await _context.Rooms.SingleOrDefaultAsync(p => p.RoomId == rid1);
                    RoomCategory cate1 = await _context.RoomCategories.SingleOrDefaultAsync(p => p.CategoryId == room1.CategoryId);
                    ViewBag.price1 = cate1.Price;
                    Room room2 = _context.Rooms.SingleOrDefault(p => p.RoomId == rid2);
                    RoomCategory cate2 = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room2.CategoryId);
                    ViewBag.price2 = cate2.Price;
                    decimal? price1 = 0;
                    decimal? price2 = 0;
                    if (cate1 != null)
                    {
                        price1 = cate1.Price;
                        ViewBag.price1 = price1;
                    }
                    if (cate2 != null)
                    {
                        price2 = cate2.Price;
                        ViewBag.price2 = price2;
                    }
                    Fee fee = _context.Fees.SingleOrDefault(p => p.UserId == uid);
                    if (act == "add")
                    {
                        if ((fee.Fee1 + price2 * 4) < (price1 * 4))
                        {
                            ViewBag.error = $"Số dư tài khoản của bạn thiếu {(int)(price1 * 4 - price2 * 4 - fee.Fee1)}đ để đổi phòng.";
                        }
                        else
                        {
                            Order order = await _context.Orders.AsNoTracking().SingleOrDefaultAsync(p => p.UserId == uid && p.SemesterId == "Su23");
                            if (order != null)
                            {
                                _context.Orders.Remove(order);
                            }
                            Order order1 = _context.Orders.SingleOrDefault(p => p.UserId == sid && p.SemesterId == "Su23");
                            if (order1 != null)
                            {
                                _context.Orders.Remove(order1);
                            }
                            Order o = new Order();
                            o.SemesterId = "Su23";
                            o.UserId = uid;
                            o.RoomId = rid1;
                            o.BedId = int.Parse(bid1);
                            _context.Entry(o).State = EntityState.Detached;
                            await _context.Orders.AddAsync(o);
                            Order o1 = new Order();
                            o1.SemesterId = "Su23";
                            o1.UserId = sid;
                            o1.RoomId = rid2;
                            o1.BedId = int.Parse(bid2);
                            _context.Entry(o1).State = EntityState.Detached;
                            await _context.Orders.AddAsync(o1);
                            Fee fee1 = _context.Fees.SingleOrDefault(p => p.UserId == sid);
                            fee1.Fee1 = fee1.Fee1 + price1 * 4 - price2 * 4;
                            _context.Fees.Update(fee1);
                            List<ChangeRoomRequest> req = await _context.ChangeRoomRequests.Where(p => p.UserId2 == uid).ToListAsync();
                            foreach (ChangeRoomRequest r in req)
                            {
                                _context.ChangeRoomRequests.Remove(r);
                            }
                            await _context.SaveChangesAsync();
                        }
                    }
                    else if (act == "delete")
                    {
                        Fee fee1 = _context.Fees.SingleOrDefault(p => p.UserId == sid);
                        fee1.Fee1 = fee1.Fee1 + price1 * 4 - price2 * 4;
                        _context.Fees.Update(fee1);
                        ChangeRoomRequest req = await _context.ChangeRoomRequests.SingleOrDefaultAsync(p => p.UserId1 == sid);
                        _context.ChangeRoomRequests.Remove(req);
                        await _context.SaveChangesAsync();
                    }
                }
                var request = await _context.ChangeRoomRequests.Where(p => p.UserId2 == uid).ToListAsync();
                return View(request);
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        public async Task<IActionResult> RequestChangeRoom(string uid, string id, string roomid)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                ViewBag.Current = "ChangeRoom";
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                Bed beds = _context.Beds.SingleOrDefault(p => p.RoomId.Equals(roomid) && p.BedId == int.Parse(id));
                Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == roomid);
                RoomCategory cate = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                Order order = _context.Orders.SingleOrDefault(p => p.UserId == u.UserId && p.SemesterId == "Su23");
                decimal? price = 0;
                if (order != null)
                {
                    Room room1 = _context.Rooms.SingleOrDefault(p => p.RoomId == order.RoomId);
                    RoomCategory cate1 = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room1.CategoryId);
                    if (cate1 != null)
                    {
                        price = cate1.Price;
                    }
                }
                ViewBag.order = order;
                ViewBag.cate = cate;
                ViewBag.room = room;
                ViewBag.roomid = roomid;
                ViewBag.id = id;
                ViewBag.uid = uid;
                Fee fee = _context.Fees.SingleOrDefault(p => p.UserId == u.UserId);
                ViewBag.Fee = fee;
                u.Fee = fee;
                if ((fee.Fee1 + price * 4) < (cate.Price * 4))
                {
                    ViewBag.error = $"Số dư tài khoản của bạn thiếu {(int)(cate.Price * 4 - price * 4 - fee.Fee1)}đ để đặt phòng.";
                }
                return View();
            }
            else
            {
                return View("Error_Authourized");
            }
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> RequestChangeRoom(string sid, string rid, string bid, string uid, string id,
            string roomid, [Bind("UserId1,UserId2,RoomId1,RoomId2,BedId1,BedId2")] ChangeRoomRequest change)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == false)
            {
                User u = JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user"));
                Bed beds = _context.Beds.SingleOrDefault(p => p.RoomId.Equals(roomid) && p.BedId == int.Parse(id));
                Room room = _context.Rooms.SingleOrDefault(p => p.RoomId == roomid);
                RoomCategory cate = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room.CategoryId);
                Order order = _context.Orders.SingleOrDefault(p => p.UserId == u.UserId && p.SemesterId == "Su23");
                decimal? price = 0;
                if (order != null)
                {
                    Room room1 = _context.Rooms.SingleOrDefault(p => p.RoomId == order.RoomId);
                    RoomCategory cate1 = _context.RoomCategories.SingleOrDefault(p => p.CategoryId == room1.CategoryId);
                    if (cate1 != null)
                    {
                        price = cate1.Price;
                    }
                }
                ViewBag.order = order;
                ViewBag.cate = cate;
                ViewBag.room = room;
                ViewBag.roomid = roomid;
                ViewBag.id = id;
                ViewBag.uid = uid;
                Fee fee = _context.Fees.SingleOrDefault(p => p.UserId == u.UserId);
                ViewBag.Fee = fee;
                u.Fee = fee;
                if ((fee.Fee1 + price * 4) < (cate.Price * 4))
                {
                    ViewBag.error = $"Số dư tài khoản của bạn thiếu {(int)(cate.Price * 4 - price * 4 - fee.Fee1)}đ để đặt phòng.";
                    return View(change);
                }
                else
                {
                    change.UserId1 = u.UserId;
                    if (order != null)
                    {
                        change.RoomId1 = order.RoomId;
                        change.BedId1 = order.BedId;
                    }
                    change.UserId2 = uid;
                    change.RoomId2 = roomid;
                    change.BedId2 = int.Parse(id);
                    fee.Fee1 = fee.Fee1 + (price * 4) - (cate.Price * 4);
                    u.Fee = fee;
                    _context.ChangeRoomRequests.Add(change);
                    _context.Fees.Update(fee);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
            }
            else
            {
                return View("Error_Authourized");
            }
        }
    }
}
