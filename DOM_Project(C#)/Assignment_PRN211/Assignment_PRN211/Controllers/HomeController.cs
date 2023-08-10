using Assignment_PRN211.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System.Diagnostics;

namespace Assignment_PRN211.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private readonly Assignment_PRN211Context _context;
        private readonly IHttpContextAccessor _httpContext;

        public HomeController(Assignment_PRN211Context context, IHttpContextAccessor httpContext, ILogger<HomeController> logger)
        {
            _context = context;
            _httpContext = httpContext;
            _logger = logger;
        }

        public IActionResult Index()
        {
            ViewBag.Current = "Home";
            List<RoomCategory> roomCategories = _context.RoomCategories.ToList();
            return View(roomCategories);
        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}