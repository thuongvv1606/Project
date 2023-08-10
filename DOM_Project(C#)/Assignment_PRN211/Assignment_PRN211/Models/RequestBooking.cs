using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class RequestBooking
    {
        public string? UserId { get; set; }
        public string RoomId { get; set; } = null!;
        public int BedId { get; set; }

        public virtual Bed Bed { get; set; } = null!;
        public virtual User? User { get; set; }
    }
}
