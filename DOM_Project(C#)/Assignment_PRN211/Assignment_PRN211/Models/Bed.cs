using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class Bed
    {
        public Bed()
        {
            ChangeRoomRequestBedNavigations = new HashSet<ChangeRoomRequest>();
            ChangeRoomRequestBeds = new HashSet<ChangeRoomRequest>();
            Orders = new HashSet<Order>();
        }

        public int BedId { get; set; }
        public string RoomId { get; set; } = null!;

        public virtual Room Room { get; set; } = null!;
        public virtual RequestBooking? RequestBooking { get; set; }
        public virtual ICollection<ChangeRoomRequest> ChangeRoomRequestBedNavigations { get; set; }
        public virtual ICollection<ChangeRoomRequest> ChangeRoomRequestBeds { get; set; }
        public virtual ICollection<Order> Orders { get; set; }
    }
}
