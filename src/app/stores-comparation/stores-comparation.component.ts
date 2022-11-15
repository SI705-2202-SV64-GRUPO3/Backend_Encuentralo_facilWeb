import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductCartItem } from 'src/models/Product';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-stores-comparation',
  templateUrl: './stores-comparation.component.html',
  styleUrls: ['./stores-comparation.component.scss'],
})
export class StoresComparationComponent implements OnInit {
  stores = [
    {
      id: 1,
      name: 'Store 1',
      total: 3.5,
      address: 'av store 111',
      logo: 'data:image/png;base64,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',
    },
    {
      id: 2,
      name: 'Store 2',
      total: 7.6,
      address: 'av store 222',
      logo: 'data:image/png;base64,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',
    },
    {
      id: 3,
      name: 'Store 3',
      total: 6.4,
      address: 'av store 333',
      logo: 'data:image/png;base64,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',
    },
  ];

  constructor(private router: Router, private cartService: CartService) {}

  ngOnInit(): void {
    this.cartService.currentCartItems.subscribe((items) => console.log(items));
  }

  selectStore(id: string | number) {
    this.router.navigateByUrl('/store/checkout');
  }
}
