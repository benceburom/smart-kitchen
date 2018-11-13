import { Component, OnInit } from '@angular/core';
import { NavParams, ModalController } from '@ionic/angular';
import { Toast } from '../../toast/toast';
import { AddKitchenDTO } from '../../model/AddKitchenDTO';
import { UserDTO } from '../../model/UserDTO';
import { UserService } from '../../services/user.service';
import { AuthenticationService } from '../../services/authentication.service';
import { KitchenService } from '../../services/kitchen.service';

@Component({
  selector: 'app-add-user-to-kitchen-modal',
  templateUrl: './add-user-to-kitchen-modal.page.html',
  styleUrls: ['./add-user-to-kitchen-modal.page.scss'],
})
export class AddUserToKitchenModalPage implements OnInit {
  addKitchenDTO: AddKitchenDTO;
  kitchenId: number;
  users: UserDTO[];
  filteredUsers: UserDTO[];
  userIds: number[];

  constructor(private navParams: NavParams,
    private modalController: ModalController,
    private userService: UserService,
    private toast: Toast,
    private authService: AuthenticationService,
    private kitchenService: KitchenService) { }

  ngOnInit() {
    this.addKitchenDTO = new AddKitchenDTO;
    this.kitchenId = this.navParams.get('custom_id');
    this.getUsers();
  }

  getUsers() {
    this.userService.listUsers().subscribe(async userList => {
      this.users = await this.removeUsersFromList(userList);
      this.filteredUsers = await this.removeUsersFromList(userList);
    });
  }

  filterItems(e) {
    this.filteredUsers = this.users;
    this.filteredUsers = this.filteredUsers.filter(user => user.userName.toLowerCase().indexOf(e.target.value.toLowerCase()) > -1);
  }

  addUserToKitchen(userId: number) {
    this.addKitchenDTO.userId = userId;
    this.addKitchenDTO.kitchenId = this.kitchenId;
    this.userService.addUserToKitchen(this.addKitchenDTO).subscribe(() => {
    }, () => {
    }, () => {
      this.toast.presentToastWithOptions({
        message: 'User added to kitchen',
        duration: 2000,
        showCloseButton: true,
        position: 'bottom',
        color: 'success',
        closeButtonText: 'Close'
      });
      this.modalController.dismiss();
    });
  }

  async removeUsersFromList(userList: UserDTO[]) {
    const ids = await this.kitchenService.getUserIdsInKitchen(this.kitchenId);
    console.log(ids);
    const returnList = new Array();
    userList.forEach(user => returnList.push(user));
    ids.forEach(id => {
      returnList.splice(returnList.findIndex(user => user.id === id), 1);
    });
    return returnList;
  }

  closeModal() {
    this.modalController.dismiss();
  }

}
