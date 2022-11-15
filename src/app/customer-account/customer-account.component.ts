import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { User } from 'src/models/User';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-customer-account',
  templateUrl: './customer-account.component.html',
  styleUrls: ['./customer-account.component.scss'],
})
export class CustomerAccountComponent implements OnInit {
  form: FormGroup = new FormGroup({});
  user: User | null = null;

  constructor(private userService: UsersService) {}

  ngOnInit(): void {
    const user = this.userService.getUser();

    if (user) {
      this.user = user;
      this.form = new FormGroup({
        firstName: new FormControl(user.firstName),
        lastName: new FormControl(user.lastName),
        email: new FormControl(user.email),
      });
    }
  }

  onSubmit(data: any) {
    if (this.user && this.user.id) {
      const id = this.user.id;
      this.userService.updateUser(id, data.value).subscribe(() => {
        this.userService.fetchUserInfo(id).subscribe((data) => {
          this.userService.setUser(data);
        });
      });
    }
  }
}
