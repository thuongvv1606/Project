using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class Order
    {
        public string UserId { get; set; } = null!;
        public string SemesterId { get; set; } = null!;
        public int? BedId { get; set; }
        public string? RoomId { get; set; }

        public virtual Bed? Bed { get; set; }
        public virtual Semester Semester { get; set; } = null!;
        public virtual User User { get; set; } = null!;
    }
}
