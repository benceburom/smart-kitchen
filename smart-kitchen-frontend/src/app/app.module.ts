import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationService } from './services/authentication.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/token.interceptor';
import { IonicStorageModule } from '@ionic/storage';
import { UserService } from './services/user.service';
import { KitchenService } from './services/kitchen.service';
import { IngredientService } from './services/ingredient.service';
import { WishListService } from './services/wish-list.service';
import { LogOutPopoverPageModule } from './pages/log-out-popover/log-out-popover.module';
import { AddIngredientPopoverPageModule } from './pages/add-ingredient-popover/add-ingredient-popover.module';
import { AddFoodPopoverPageModule } from './pages/add-food-popover/add-food-popover.module';
import { Toast } from './toast/toast';
import { AddUserToKitchenModalPageModule } from './pages/add-user-to-kitchen-modal/add-user-to-kitchen-modal.module';
import { BackButtonService } from './services/back-button.service';

@NgModule({
    declarations: [AppComponent],
    entryComponents: [],
    imports: [BrowserModule,
        IonicModule.forRoot(),
        AppRoutingModule,
        HttpClientModule,
        IonicStorageModule.forRoot(),
        LogOutPopoverPageModule,
        AddIngredientPopoverPageModule,
        AddFoodPopoverPageModule,
        AddUserToKitchenModalPageModule],
    providers: [
        StatusBar,
        SplashScreen,
        { provide: RouteReuseStrategy, useClass: IonicRouteStrategy },
        AuthenticationService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptor,
            multi: true,
        },
        UserService,
        KitchenService,
        IngredientService,
        WishListService,
        Toast,
        BackButtonService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
