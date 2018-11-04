import { NewIngredientDTO } from './NewIngredientDTO';

export class FoodDTO {
    name: String;
    kitchenId: number;
    ingredients: NewIngredientDTO[];
}
