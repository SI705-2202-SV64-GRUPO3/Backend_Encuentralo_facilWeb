export class User {
  public id?: string;

  constructor(
    public firstName: string,
    public lastName: string,
    public email: string,
    public password: string
  ) {}
}
