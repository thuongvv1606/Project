using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class ChangeRoomRequest
    {
        public string UserId1 { get; set; } = null!;
        public string? UserId2 { get; set; }
        public string? RoomId1 { get; set; }
        public int? BedId1 { get; set; }
        public string? RoomId2 { get; set; }
        public int? BedId2 { get; set; }

        public virtual Bed? Bed { get; set; }
        public virtual Bed? BedNavigation { get; set; }
        public virtual User UserId1Navigation { get; set; } = null!;
        public virtual User? UserId2Navigation { get; set; }
    }
}
