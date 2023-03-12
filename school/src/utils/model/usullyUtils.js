import {Message} from 'element-ui'

export const tip=(obj,title,key,color)=>{
    if (!color)
    {
        color='color: teal'
    }

        const h = obj.$createElement;
        obj.$notify({
        title: title,
        message: h('i', { style: color }, key)
      });

}
export const message=(code,text)=>{
    if(code==200)
    {
        Message.success({message:code+"  "+ text})
    }
    else
    {
        Message.error({message:code+"  "+ text})
    }
    
}