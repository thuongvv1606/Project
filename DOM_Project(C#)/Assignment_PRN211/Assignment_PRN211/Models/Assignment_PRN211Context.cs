using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace Assignment_PRN211.Models
{
    public partial class Assignment_PRN211Context : DbContext
    {
        public Assignment_PRN211Context()
        {
        }

        public Assignment_PRN211Context(DbContextOptions<Assignment_PRN211Context> options)
            : base(options)
        {
        }

        public virtual DbSet<Bed> Beds { get; set; } = null!;
        public virtual DbSet<ChangeRoomRequest> ChangeRoomRequests { get; set; } = null!;
        public virtual DbSet<Dormitory> Dormitories { get; set; } = null!;
        public virtual DbSet<Fee> Fees { get; set; } = null!;
        public virtual DbSet<Order> Orders { get; set; } = null!;
        public virtual DbSet<RequestBooking> RequestBookings { get; set; } = null!;
        public virtual DbSet<RequestContact> RequestContacts { get; set; } = null!;
        public virtual DbSet<Room> Rooms { get; set; } = null!;
        public virtual DbSet<RoomCategory> RoomCategories { get; set; } = null!;
        public virtual DbSet<Semester> Semesters { get; set; } = null!;
        public virtual DbSet<User> Users { get; set; } = null!;

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see http://go.microsoft.com/fwlink/?LinkId=723263.
                optionsBuilder.UseSqlServer("server =(local); database = Assignment_PRN211;uid=sa;pwd=123;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Bed>(entity =>
            {
                entity.HasKey(e => new { e.BedId, e.RoomId })
                    .HasName("PK__Bed__2B8F73D3F512C171");

                entity.ToTable("Bed");

                entity.Property(e => e.RoomId)
                    .HasMaxLength(4)
                    .IsUnicode(false);

                entity.HasOne(d => d.Room)
                    .WithMany(p => p.Beds)
                    .HasForeignKey(d => d.RoomId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Bed__RoomId__36B12243");
            });

            modelBuilder.Entity<ChangeRoomRequest>(entity =>
            {
                entity.HasKey(e => e.UserId1)
                    .HasName("PK__ChangeRo__2AD6E8A891C88042");

                entity.ToTable("ChangeRoomRequest");

                entity.Property(e => e.UserId1)
                    .HasMaxLength(8)
                    .IsUnicode(false);

                entity.Property(e => e.RoomId1)
                    .HasMaxLength(4)
                    .IsUnicode(false);

                entity.Property(e => e.RoomId2)
                    .HasMaxLength(4)
                    .IsUnicode(false);

                entity.Property(e => e.UserId2)
                    .HasMaxLength(8)
                    .IsUnicode(false);

                entity.HasOne(d => d.UserId1Navigation)
                    .WithOne(p => p.ChangeRoomRequestUserId1Navigation)
                    .HasForeignKey<ChangeRoomRequest>(d => d.UserId1)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__ChangeRoo__UserI__37A5467C");

                entity.HasOne(d => d.UserId2Navigation)
                    .WithMany(p => p.ChangeRoomRequestUserId2Navigations)
                    .HasForeignKey(d => d.UserId2)
                    .HasConstraintName("FK__ChangeRoo__UserI__38996AB5");

                entity.HasOne(d => d.Bed)
                    .WithMany(p => p.ChangeRoomRequestBeds)
                    .HasForeignKey(d => new { d.BedId1, d.RoomId1 })
                    .HasConstraintName("FK__ChangeRoomReques__398D8EEE");

                entity.HasOne(d => d.BedNavigation)
                    .WithMany(p => p.ChangeRoomRequestBedNavigations)
                    .HasForeignKey(d => new { d.BedId2, d.RoomId2 })
                    .HasConstraintName("FK__ChangeRoomReques__3A81B327");
            });

            modelBuilder.Entity<Dormitory>(entity =>
            {
                entity.HasKey(e => e.DomId)
                    .HasName("PK__Dormitor__4F74B1F308DA38CA");

                entity.ToTable("Dormitory");

                entity.Property(e => e.DomId).ValueGeneratedNever();

                entity.Property(e => e.DomName)
                    .HasMaxLength(1)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<Fee>(entity =>
            {
                entity.HasKey(e => e.UserId)
                    .HasName("PK__Fee__1788CC4C56B014A1");

                entity.ToTable("Fee");

                entity.Property(e => e.UserId)
                    .HasMaxLength(8)
                    .IsUnicode(false);

                entity.Property(e => e.Fee1)
                    .HasColumnType("money")
                    .HasColumnName("Fee");

                entity.HasOne(d => d.User)
                    .WithOne(p => p.Fee)
                    .HasForeignKey<Fee>(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Fee__UserId__3B75D760");
            });

            modelBuilder.Entity<Order>(entity =>
            {
                entity.HasKey(e => new { e.UserId, e.SemesterId })
                    .HasName("PK__Order__D7CBFC51B4200C05");

                entity.ToTable("Order");

                entity.Property(e => e.UserId)
                    .HasMaxLength(8)
                    .IsUnicode(false);

                entity.Property(e => e.SemesterId)
                    .HasMaxLength(4)
                    .IsUnicode(false);

                entity.Property(e => e.RoomId)
                    .HasMaxLength(4)
                    .IsUnicode(false);

                entity.HasOne(d => d.Semester)
                    .WithMany(p => p.Orders)
                    .HasForeignKey(d => d.SemesterId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Order__SemesterI__3D5E1FD2");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.Orders)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Order__UserId__3E52440B");

                entity.HasOne(d => d.Bed)
                    .WithMany(p => p.Orders)
                    .HasForeignKey(d => new { d.BedId, d.RoomId })
                    .HasConstraintName("FK__Order__3C69FB99");
            });

            modelBuilder.Entity<RequestBooking>(entity =>
            {
                entity.HasKey(e => new { e.BedId, e.RoomId })
                    .HasName("PK__RequestB__2B8F73D3E332122B");

                entity.ToTable("RequestBooking");

                entity.Property(e => e.RoomId)
                    .HasMaxLength(4)
                    .IsUnicode(false);

                entity.Property(e => e.UserId)
                    .HasMaxLength(8)
                    .IsUnicode(false);

                entity.HasOne(d => d.User)
                    .WithMany(p => p.RequestBookings)
                    .HasForeignKey(d => d.UserId)
                    .HasConstraintName("FK__RequestBo__UserI__3F466844");

                entity.HasOne(d => d.Bed)
                    .WithOne(p => p.RequestBooking)
                    .HasForeignKey<RequestBooking>(d => new { d.BedId, d.RoomId })
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__RequestBooking__403A8C7D");
            });

            modelBuilder.Entity<RequestContact>(entity =>
            {
                entity.HasNoKey();

                entity.ToTable("RequestContact");

                entity.Property(e => e.Message).HasMaxLength(1000);

                entity.Property(e => e.Phone)
                    .HasMaxLength(10)
                    .IsUnicode(false);

                entity.Property(e => e.UserId)
                    .HasMaxLength(8)
                    .IsUnicode(false);

                entity.HasOne(d => d.User)
                    .WithMany()
                    .HasForeignKey(d => d.UserId)
                    .HasConstraintName("FK__RequestCo__UserI__412EB0B6");
            });

            modelBuilder.Entity<Room>(entity =>
            {
                entity.ToTable("Room");

                entity.Property(e => e.RoomId)
                    .HasMaxLength(4)
                    .IsUnicode(false);

                entity.HasOne(d => d.Category)
                    .WithMany(p => p.Rooms)
                    .HasForeignKey(d => d.CategoryId)
                    .HasConstraintName("FK__Room__CategoryId__4222D4EF");

                entity.HasOne(d => d.Dom)
                    .WithMany(p => p.Rooms)
                    .HasForeignKey(d => d.DomId)
                    .HasConstraintName("FK__Room__DomId__4316F928");
            });

            modelBuilder.Entity<RoomCategory>(entity =>
            {
                entity.HasKey(e => e.CategoryId)
                    .HasName("PK__RoomCate__19093A0B5D35861E");

                entity.ToTable("RoomCategory");

                entity.Property(e => e.CategoryId).ValueGeneratedNever();

                entity.Property(e => e.CategoryName).HasMaxLength(255);

                entity.Property(e => e.Price).HasColumnType("money");
            });

            modelBuilder.Entity<Semester>(entity =>
            {
                entity.ToTable("Semester");

                entity.Property(e => e.SemesterId)
                    .HasMaxLength(4)
                    .IsUnicode(false);

                entity.Property(e => e.EndTime).HasColumnType("date");

                entity.Property(e => e.SemesterName).HasMaxLength(255);

                entity.Property(e => e.StartTime).HasColumnType("date");
            });

            modelBuilder.Entity<User>(entity =>
            {
                entity.ToTable("User");

                entity.Property(e => e.UserId)
                    .HasMaxLength(8)
                    .IsUnicode(false);

                entity.Property(e => e.IsAdmin).HasColumnName("isAdmin");

				entity.Property(e => e.IsActive).HasColumnName("isActive");

				entity.Property(e => e.Password).HasMaxLength(255);

                entity.Property(e => e.UserDob).HasColumnType("date");

                entity.Property(e => e.UserName).HasMaxLength(255);
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
