import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JwtAuthenticationResponse} from '../model/JwtAuthenticationResponse';
import {map} from 'rxjs/operators';
import {SignUpRequest} from '../model/SignUpRequest';
import {ApiResponse} from '../model/ApiResponse';
import {UserSummary} from '../model/UserSummary';
import {LoginRequest} from '../model/LoginRequest';
import {Observable} from 'rxjs';
import {Storage} from '@ionic/storage';

@Injectable()
export class AuthenticationService {
    storedToken: String;

    private authUrl = 'http://192.168.0.24:8080/api/auth';

    constructor(private http: HttpClient, private storage: Storage) {
        this.storedToken = '';
    }

    login(loginRequest: LoginRequest): Observable<boolean> {
        return this.http.post<JwtAuthenticationResponse>(
            this.authUrl + '/signin',
            loginRequest
        ).pipe(map(data => {
            if (data.accessToken) {
                this.storage.set('currentUser', JSON.stringify({username: loginRequest.usernameOrEmail, token: data.accessToken}));
                return true;
            } else {
                return false;
            }
        }));
    }

    signUp(signUpRequest: SignUpRequest): Observable<ApiResponse> {
        return this.http.post<ApiResponse>(this.authUrl + '/signup', signUpRequest);
    }

    getToken(): String {
        let currentUser;
        this.storage.get('currentUser').then(user => {
            currentUser = JSON.parse(user);
            const token = currentUser && currentUser.token;
            token ? this.storedToken = token : this.storedToken = '';
        });
        return this.storedToken;
    }

    logout(): void {
        this.storage.remove('currentUser');
        this.storedToken = null;
    }

    isLoggedIn(): boolean {
        const token: String = this.getToken();
        return token && token.length > 0;
    }

    getCurrentUser(): Observable<UserSummary> {
        return this.http.get<UserSummary>('http://192.168.0.24:8080/api/user/me');
    }

}
