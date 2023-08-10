using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class RoomCategory
    {
        public RoomCategory()
        {
            Rooms = new HashSet<Room>();
        }

        public int CategoryId { get; set; }
        public string? CategoryName { get; set; }
        public decimal? Price { get; set; }

        public virtual ICollection<Room> Rooms { get; set; }
    }
}
