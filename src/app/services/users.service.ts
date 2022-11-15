import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from 'src/models/User';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  constructor(private http: HttpClient) {}

  public register(user: User): Observable<void> {
    return this.http.post<void>('http://localhost:8080/clients', user);
  }

  public login(email: string, password: string): Observable<void> {
    const body = {
      email,
      password,
    };

    return this.http.post<void>('http://localhost:8080/clients/login', body);
  }

  public fetchUserInfo(id: string | number): Observable<User> {
    return this.http.get<User>(`http://localhost:8080/clients/${id}`);
  }

  public updateUser(id: string | number, user: User) {
    return this.http.put<void>(`http://localhost:8080/clients/${id}`, user);
  }

  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  public getUser() {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  public removeUser() {
    localStorage.removeItem('user');
  }
}
