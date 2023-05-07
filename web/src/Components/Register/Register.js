import React, { useState } from "react";
import { Step1 } from "./RegisterSteps/Step1";
import { Step2 } from "./RegisterSteps/Step2";
import { Step2Tutor } from "./RegisterSteps/Step2Tutor";
import { Step3 } from "./RegisterSteps/Step3";
import { Step4 } from "./RegisterSteps/Step4";
export function Register(){
    const [hide1,setHide1]=useState(false);
    const [hide2,setHide2]=useState(false);
    const [hide3,setHide3]=useState(false);
    const [student,setStudent]=useState(false);
    const [tutor,setTutor]=useState(false);
    const [id,setId]=useState();
    const getId = (id) => {
        setId(id);
    }
    const [user,setUser]=useState();
    const getUser=(user)=>{
        setUser(user);
    }
    const getHide1=(hide1,student,tutor)=>{
        setHide1(hide1)
        setStudent(student)
        setTutor(tutor)
    }
    const getHide2=(hide2)=>{
        setHide2(hide2)
    }
    const getHide3=(hide3)=>{
        setHide3(hide3)
    }
    return (
    <div>
        {!hide1 && <Step1 Onsubmit={getUser} Onsubmit1={getHide1}/>}
        {!hide2 && hide1 && student &&<Step2 user={user} Onsubmit={getId} Onsubmit2={getHide2}/>}
        {!hide2 && hide1 && tutor &&<Step2Tutor user={user} Onsubmit={getId} Onsubmit2={getHide2}/>}
        {!hide3 && hide2  && <Step3 id={id} Onsubmit3={getHide3} />}
        {hide3 && <Step4 /> }
    </div>
    );
}