using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class User
    {
        public User()
        {
            ChangeRoomRequestUserId2Navigations = new HashSet<ChangeRoomRequest>();
            Orders = new HashSet<Order>();
            RequestBookings = new HashSet<RequestBooking>();
        }

        public string? UserName { get; set; }
        public string UserId { get; set; } = null!;
        public DateTime? UserDob { get; set; }
        public bool? UserGender { get; set; }
        public string? Password { get; set; }
        public bool? IsAdmin { get; set; }
		public bool? IsActive { get; set; }

		public virtual ChangeRoomRequest? ChangeRoomRequestUserId1Navigation { get; set; }
        public virtual Fee? Fee { get; set; }
        public virtual ICollection<ChangeRoomRequest> ChangeRoomRequestUserId2Navigations { get; set; }
        public virtual ICollection<Order> Orders { get; set; }
        public virtual ICollection<RequestBooking> RequestBookings { get; set; }
    }
}
