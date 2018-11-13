import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Storage } from '@ionic/storage';
import { LoginRequest } from '../model/LoginRequest';
import { Observable } from 'rxjs';
import { JwtAuthenticationResponse } from '../model/JwtAuthenticationResponse';
import { map } from 'rxjs/operators';
import { SignUpRequest } from '../model/SignUpRequest';
import { ApiResponse } from '../model/ApiResponse';
import { UserSummary } from '../model/UserSummary';
import { Toast } from '../toast/toast';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private authUrl = 'http://192.168.0.24:8080/api/auth';

    constructor(private http: HttpClient, private storage: Storage, private toast: Toast) {
    }

    login(loginRequest: LoginRequest): Promise<boolean> {
        return this.http.post<JwtAuthenticationResponse>(
            this.authUrl + '/signin',
            loginRequest
        ).toPromise().then(async data => {
            if (data.accessToken) {
                await this.storage.set('currentUser', JSON.stringify({
                    username: loginRequest.usernameOrEmail,
                    token: data.accessToken
                }));
                return true;
            } else {
                return false;
            }
        });
    }

    signUp(signUpRequest: SignUpRequest): Observable<ApiResponse> {
        return this.http.post<ApiResponse>(this.authUrl + '/signup', signUpRequest);
    }

    getToken(): Promise<String> {
        let currentUser;
        return new Promise(resolve => {
            this.storage.get('currentUser').then(user => {
                currentUser = JSON.parse(user);
                const token = currentUser && currentUser.token;
                token ? resolve(token) : resolve('');
            });
        });
    }

    logout(): void {
        this.storage.remove('currentUser');
    }

    getCurrentUser(): Observable<UserSummary> {
        return this.http.get<UserSummary>('http://192.168.0.24:8080/api/user/me');
    }
}
