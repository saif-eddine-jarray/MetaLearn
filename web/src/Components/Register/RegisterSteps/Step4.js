import React from "react";
import { Card } from 'primereact/card';
import { Button } from "primereact/button";
import { useNavigate } from "react-router-dom";
import { Steps } from 'primereact/steps';
import './Step4.css'
export function Step4(){
    let navigate=useNavigate();
    const handleClick=()=>{
        navigate('/login')
    }
    const footer = <span>
                        <Button label="Login" icon="pi pi-check" style={{width: '90px',height: '25px', marginRight:'auto',marginLeft:'auto'}} onClick={()=>{handleClick()}}/>
                    </span>;
     const items = [
        {label: 'Register'},
        {label: 'User details'},
        {label: 'Validation'},
        {label: 'Finalization'}
    ];
    return(
        <div>
            <Steps model={items} className='Steps' activeIndex={3}/>
            <Card title='Thank you !' footer={footer} className='card'>
                your account has been been verified successfully. Enjoy your journey with us :) 
            </Card>
        </div>
    );
}