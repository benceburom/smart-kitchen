import {Injectable, Output, EventEmitter} from '@angular/core';
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
    @Output() getLoggedInName: EventEmitter<any> = new EventEmitter();

    private authUrl = 'http://localhost:8080/api/auth';

    constructor(private http: HttpClient, private storage: Storage) {
    }

    login(loginRequest: LoginRequest): Observable<boolean> {
        return this.http.post<JwtAuthenticationResponse>(
            this.authUrl + '/signin',
            {loginRequest: LoginRequest}
        ).pipe(map(data => {
            if (data.accessToken) {
                this.storage.set('currentUser', JSON.stringify({username: loginRequest.usernameOrEmail, token: data.accessToken}));
                this.getLoggedInName.emit(loginRequest.usernameOrEmail);
                return true;
            } else {
                this.getLoggedInName.emit(loginRequest.usernameOrEmail);
                return false;
            }
        }));
    }

    signUp(user: SignUpRequest): Observable<ApiResponse> {
        return this.http.post<ApiResponse>(this.authUrl + '/signup',
            {
                name: user.name,
                username: user.username,
                email: user.email,
                password: user.password
            });
    }

    getToken(): String {
        let currentUser;
        this.storage.get('currentUser').then(user => {
            currentUser = JSON.parse(user);
        });
        const token = currentUser && currentUser.token;
        return token ? token : '';
    }

    logout(): void {
        this.getLoggedInName.emit('');
        this.storage.remove('currentUser');
    }

    isLoggedIn(): boolean {
        const token: String = this.getToken();
        return token && token.length > 0;
    }

    getCurrentUserName(): Observable<string> {
        return this.http.get<UserSummary>('http://localhost:8080/api/user/me')
            .pipe(map(data => {
                return data.username;
            }));
    }

}
