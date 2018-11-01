import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Storage} from '@ionic/storage';
import {LoginRequest} from '../model/LoginRequest';
import {Observable} from 'rxjs';
import {JwtAuthenticationResponse} from '../model/JwtAuthenticationResponse';
import {map} from 'rxjs/operators';
import {SignUpRequest} from '../model/SignUpRequest';
import {ApiResponse} from '../model/ApiResponse';
import {UserSummary} from '../model/UserSummary';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private authUrl = 'http://192.168.0.24:8080/api/auth';

    constructor(private http: HttpClient, private storage: Storage) {
    }

    login(loginRequest: LoginRequest): Observable<boolean> {
        return this.http.post<JwtAuthenticationResponse>(
            this.authUrl + '/signin',
            loginRequest
        ).pipe(map(data => {
            console.log('login response map started');
            if (data.accessToken) {
                this.storage.set('currentUser', JSON.stringify({
                    username: loginRequest.usernameOrEmail,
                    token: data.accessToken
                })).then(() => {
                    console.log('token setted in current user');
                    return true;
                });
            } else {
                console.log('return false');
                return false;
            }
        }));
    }

    signUp(signUpRequest: SignUpRequest): Observable<ApiResponse> {
        return this.http.post<ApiResponse>(this.authUrl + '/signup', signUpRequest);
    }

    getToken() {
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

    // isLoggedIn(): boolean {
    //     const token: String = this.getToken();
    //     return token && token.length > 0;
    // }

    getCurrentUser(): Observable<UserSummary> {
        return this.http.get<UserSummary>('http://192.168.0.24:8080/api/user/me');
    }
}
