export class Product {
  constructor(
    public id: number | string,
    public name: string,
    public imageUrl: string,
    public description?: string
  ) {
    if (!description) this.description = '';
  }
}

export type ProductCartItem = Product & { quantity: number };
