using Assignment_PRN211.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using PagedList;

namespace Assignment_PRN211.Controllers
{
    public class SemesterController : Controller
    {
        private readonly Assignment_PRN211Context _context;
        private readonly IHttpContextAccessor _httpContext;

        public SemesterController(Assignment_PRN211Context context, IHttpContextAccessor httpContext)
        {
            _context = context;
            _httpContext = httpContext;
        }
        public IActionResult Index(string? searchStr, string? SemesterId, int? pageNumber)
        { 
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                ViewBag.Cur = "Semester";
                List<Semester> semesters = _context.Semesters.ToList();
                if (pageNumber == null) pageNumber = 1;
                int page = (pageNumber ?? 1);


                ViewBag.search = searchStr;
                ViewBag.SemesterId = SemesterId;
                if (SemesterId != null)
                {
                    semesters = semesters.Where(p => p.SemesterId == SemesterId).ToList();
                }
                if (searchStr != null)
                {
                    semesters = semesters.Where(p => p.SemesterName.Contains(searchStr)).ToList();
                }
                ViewBag.count = semesters.Count;
                ViewBag.page = page;
                return View(semesters.ToPagedList(page, 10));
            } else
            {
                return View("Error_Authourized");
            }
        }

        // GET: Semesters/Details/5
        public async Task<IActionResult> Details(string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                if (id == null || _context.Semesters == null)
                {
                    return NotFound();
                }

                var semester = await _context.Semesters
                    .FirstOrDefaultAsync(m => m.SemesterId == id);
                if (semester == null)
                {
                    return NotFound();
                }

                return View(semester);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        // GET: Semesters/Create
        public IActionResult Add()
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                return View();
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        // POST: Semesters/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Add([Bind("SemesterId,SemesterName,StartTime,EndTime")] Semester semester)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                if (semester.StartTime.Value.CompareTo(semester.EndTime.Value) >= 0)
                {
                    ViewBag.error = "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc";
                }
                else
                {
                    _context.Add(semester);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
                return View(semester);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        // GET: Semesters/Edit/5
        public async Task<IActionResult> Edit(string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                if (id == null || _context.Semesters == null)
                {
                    return NotFound();
                }

                var semester = await _context.Semesters.FindAsync(id);
                if (semester == null)
                {
                    return NotFound();
                }
                return View(semester);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        // POST: Semesters/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(string id, [Bind("SemesterId,SemesterName,StartTime,EndTime")] Semester semester)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                if (id != semester.SemesterId)
                {
                    return NotFound();
                }

                if (semester.StartTime.Value.CompareTo(semester.EndTime.Value) >= 0)
                {
                    ViewBag.error = "Thời gian bắt đầu phải nhỏ hơn thời gian kết thúc";
                }
                else
                {
                    _context.Update(semester);
                    await _context.SaveChangesAsync();
                    return RedirectToAction(nameof(Index));
                }
                return View(semester);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        // GET: Semesters/Delete/5
        public async Task<IActionResult> Delete(string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                if (id == null || _context.Semesters == null)
                {
                    return NotFound();
                }

                var semester = await _context.Semesters
                    .FirstOrDefaultAsync(m => m.SemesterId == id);
                if (semester == null)
                {
                    return NotFound();
                }

                return View(semester);
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        // POST: Semesters/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(string id)
        {
            if (_httpContext.HttpContext.Session.GetString("user") != null && JsonConvert.DeserializeObject<User>(_httpContext.HttpContext.Session.GetString("user")).IsAdmin == true)
            {
                if (_context.Semesters == null)
                {
                    return Problem("Entity set 'Assignment_PRN211Context.Semesters'  is null.");
                }
                var semester = await _context.Semesters.FindAsync(id);
                if (semester != null)
                {
                    _context.Semesters.Remove(semester);
                }

                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            else
            {
                return View("Error_Authourized");
            }
        }

        private bool SemesterExists(string id)
        {
            return (_context.Semesters?.Any(e => e.SemesterId == id)).GetValueOrDefault();
        }
    }
}
