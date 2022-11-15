import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-sucess-order',
  templateUrl: './sucess-order.component.html',
  styleUrls: ['./sucess-order.component.scss'],
})
export class SucessOrderComponent implements OnInit {
  constructor(private router: Router, private cartService: CartService) {}

  ngOnInit(): void {}

  backToStore() {
    this.cartService.setCartItems([]);
    this.router.navigateByUrl('/store');
  }
}
