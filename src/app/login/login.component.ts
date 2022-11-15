import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private usersService: UsersService, private router: Router) {}

  ngOnInit(): void {}

  onSubmit(data: FormGroup) {
    this.usersService.login(data.value.email, data.value.password).subscribe({
      next: (data) => {
        this.usersService.setUser(data);
        this.router.navigateByUrl('/store');
      },
      error: (err) => console.log(err),
    });
  }
}
