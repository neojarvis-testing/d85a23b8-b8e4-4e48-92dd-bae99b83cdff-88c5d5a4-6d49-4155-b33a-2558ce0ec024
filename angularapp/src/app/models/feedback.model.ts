import { Property } from "./property.model";
import { User } from "./user.model";

export interface Feedback {
feedbackId?:number;
feedbackText?:string;
date?:string;
userId?:number;
user?:User;
propertyId?:number;
property?:Property;
category?:string;
}