using System;
using System.Collections.Generic;

namespace Assignment_PRN211.Models
{
    public partial class Fee
    {
        public string UserId { get; set; } = null!;
        public decimal? Fee1 { get; set; }

        public virtual User User { get; set; } = null!;
    }
}
