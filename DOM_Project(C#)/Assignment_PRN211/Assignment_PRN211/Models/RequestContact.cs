using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class RequestContact
    {
        public string? UserId { get; set; }
        public string? Phone { get; set; }
        public string? Message { get; set; }

        public virtual User? User { get; set; }
    }
}
