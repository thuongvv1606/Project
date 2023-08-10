using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class Room
    {
        public Room()
        {
            Beds = new HashSet<Bed>();
        }

        public string RoomId { get; set; } = null!;
        public int? DomId { get; set; }
        public int? CategoryId { get; set; }
        public bool? Gender { get; set; }

        public virtual RoomCategory? Category { get; set; }
        public virtual Dormitory? Dom { get; set; }
        public virtual ICollection<Bed> Beds { get; set; }
    }
}
