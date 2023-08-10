using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class Dormitory
    {
        public Dormitory()
        {
            Rooms = new HashSet<Room>();
        }

        public int DomId { get; set; }
        public string? DomName { get; set; }

        public virtual ICollection<Room> Rooms { get; set; }
    }
}
