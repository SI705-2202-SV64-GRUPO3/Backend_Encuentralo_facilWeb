import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/models/Category';
import { Product, ProductCartItem } from 'src/models/Product';
import { CartService } from '../services/cart.service';
import { ProductsService } from '../services/products.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.scss'],
})
export class StoreComponent implements OnInit {
  products: Array<Product> = [];
  categories: Array<Category> = [];
  cartItems: Array<ProductCartItem> = [];

  constructor(
    private productsService: ProductsService,
    private cartService: CartService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.productsService
      .getProducts()
      .subscribe((products) => (this.products = products));

    this.productsService
      .getCategories()
      .subscribe(
        (categories) =>
          (this.categories = [{ id: 'all', name: 'Todos' }, ...categories])
      );

    this.cartService.currentCartItems.subscribe(
      (cartItems) => (this.cartItems = cartItems)
    );
  }

  onLinkClick(categoryId: string) {
    this.productsService
      .getProducts(categoryId)
      .subscribe((products) => (this.products = products));
  }

  onProductClick(product: Product) {
    const cartItems = [...this.cartItems];

    const itemIndex = cartItems.findIndex(
      (item) => item.id === product.id
    );

    if (itemIndex !== -1) {
      cartItems[itemIndex].quantity += 1;
    } else {
      cartItems.push({
        quantity: 1,
        ...product,
      });
    }

    this.cartService.setCartItems(cartItems);
  }

  onDeleteItem(id: any) {
    this.cartItems = this.cartItems.filter((item) => item.id !== id);
  }

  goToCompare() {
    this.router.navigateByUrl('/store/compare');
  }
}
