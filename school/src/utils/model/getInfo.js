import { getApi, postApi } from "@/utils/model/api";

export const getUserInfoByt = ()=>{
    let expir = window.localStorage.getItem("expir");
      let dates = new Date().getTime();
      if(expir>dates)
      {
        return  postApi("getUser");
      }
      else
      return null;
}