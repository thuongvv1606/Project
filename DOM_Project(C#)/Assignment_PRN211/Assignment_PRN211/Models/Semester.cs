using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class Semester
    {
        public Semester()
        {
            Orders = new HashSet<Order>();
        }

        public string SemesterId { get; set; } = null!;
        public string? SemesterName { get; set; }
        public DateTime? StartTime { get; set; }
        public DateTime? EndTime { get; set; }

        public virtual ICollection<Order> Orders { get; set; }
    }
}
