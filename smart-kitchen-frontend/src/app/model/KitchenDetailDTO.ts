import {IngredientDTO} from './IngredientDTO';

export class KitchenDetailDTO {
    id: number;
    name: String;
    ingredients: IngredientDTO[];
    wishListId: number;
    userIds: number[];
}
