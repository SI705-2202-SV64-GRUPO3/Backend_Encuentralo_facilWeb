import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { districts } from '../constants/districts';
import { SellerService } from '../services/seller.service';

@Component({
  selector: 'app-register-seller',
  templateUrl: './register-seller.component.html',
  styleUrls: ['./register-seller.component.scss'],
})
export class RegisterSellerComponent implements OnInit {
  public registerForm: FormGroup = new FormGroup({});
  districts = districts;

  constructor(private router: Router, private sellerService: SellerService) {}

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      name: new FormControl(''),
      district: new FormControl(''),
      email: new FormControl(''),
      password: new FormControl(''),
    });
  }

  onSubmit(data: FormGroup) {
    this.sellerService.registerSeller(data.value).subscribe({
      next: () => {
        this.router.navigateByUrl('/login-vendedor');
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  onCancelClick() {
    this.router.navigateByUrl('/login-vendedor');
  }
}
