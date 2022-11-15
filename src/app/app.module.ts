import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MainComponent } from './main/main.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { StoresComparationComponent } from './stores-comparation/stores-comparation.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { StoreComponent } from './store/store.component';
import { SucessOrderComponent } from './sucess-order/sucess-order.component';
import { CustomerAccountComponent } from './customer-account/customer-account.component';
import { SellerLoginComponent } from './seller-login/seller-login.component';
import { SellerAccountComponent } from './seller-account/seller-account.component';
import { SellerComponent } from './seller/seller.component';
import { RegisterSellerComponent } from './register-seller/register-seller.component';
import { MatSelectModule } from '@angular/material/select';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    MainComponent,
    ProductCardComponent,
    StoresComparationComponent,
    CheckoutComponent,
    StoreComponent,
    SucessOrderComponent,
    CustomerAccountComponent,
    SellerLoginComponent,
    SellerAccountComponent,
    SellerComponent,
    RegisterSellerComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatSelectModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
