import React, { useState } from 'react';
import { useFormik } from 'formik';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { Dropdown } from 'primereact/dropdown';
import { Checkbox } from 'primereact/checkbox';
import { Divider } from 'primereact/divider';
import { classNames } from 'primereact/utils';
import { Steps } from 'primereact/steps';
import axios from 'axios';
import './Step1.css';
export function Step1 (props) {

    const [nameError,setNameError]=useState(false);
    const [emailError,setEmailError]=useState(false);
    const formik = useFormik({
        initialValues: {
            name: '',
            email: '',
            password: '',
            profession:'',
            accept: false
        },
        validate: (data) => {
            let errors = {};

            if (!data.name) {
                errors.name = 'Name is required.';
            }

            if (!data.email) {
                errors.email = 'Email is required.';
            }
            else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(data.email)) {
                errors.email = 'Invalid email address. E.g. example@email.com';
            }

            if (!data.password) {
                errors.password = 'Password is required.';
            }
            if (!data.profession) {
                errors.profession = 'Profession is required.';
            }
            if (!data.accept) {
                errors.accept = 'You need to agree to the terms and conditions.';
            }

            return errors;
        },
        onSubmit: (data) => {
        setEmailError(false);
        setNameError(false);
        props.Onsubmit(data);
        axios.get(`${process.env.REACT_APP_HOST}/user/get/username=${data.name}`)
            .then((Response) => {
              if( Response.data!=''){
                setNameError(true)
              }else{
                axios.get(`${process.env.REACT_APP_HOST}/user/get/email=${data.email}`)
                .then((Response) => {
                if( Response.data.email==data.email){
                    setEmailError(true)
                }else{
                    formik.resetForm();
                    if(data.profession==2){
                        props.Onsubmit1(true,true,false);
                    }else{
                        props.Onsubmit1(true,false,true);
                    }
                }
            });
            }
        });
        }
    });

    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };
    const passwordHeader = <h6>Pick a password</h6>;
    const passwordFooter = (
        <React.Fragment>
            <Divider />
            <p className="mt-2">Suggestions</p>
            <ul className="pl-2 ml-2 mt-0" style={{ lineHeight: '1.5' }}>
                <li>At least one lowercase</li>
                <li>At least one uppercase</li>
                <li>At least one numeric</li>
                <li>Minimum 8 characters</li>
            </ul>
        </React.Fragment>
    );

    const items = [
        {label: 'Register'},
        {label: 'User details'},
        {label: 'Validation'},
        {label: 'Finalization'}
      ];
    const professions=[
        {name: 'Tutor', value: 3},
        {name: 'Student', value: 2}
    ]
    return (
        <div>
            <Steps model={items} className='Steps' activeIndex={0}/>
            <div className="form-demo">
                <div className="flex justify-content-center">
                    <div className="card">
                        <form onSubmit={formik.handleSubmit} className="p-fluid">
                            <div className="field">
                                <span className="p-float-label">
                                    <InputText id="name" name="name" value={formik.values.name} onChange={formik.handleChange} autoFocus className={classNames({ 'p-invalid': isFormFieldValid('name') || nameError  })} />
                                    <label htmlFor="name" className={classNames({ 'p-error': isFormFieldValid('name') || nameError })}>User name*</label>
                                </span>
                                {getFormErrorMessage('name')}
                                {nameError && <small className="p-error">Name already exist</small>}
                            </div>
                            <div className="field">
                                <span className="p-float-label p-input-icon-right">
                                    <i className="pi pi-envelope" />
                                    <InputText id="email" name="email" value={formik.values.email} onChange={formik.handleChange} className={classNames({ 'p-invalid': isFormFieldValid('email') || emailError })} />
                                    <label htmlFor="email" className={classNames({ 'p-error': isFormFieldValid('email') || emailError })}>Email*</label>
                                </span>
                                {getFormErrorMessage('email')}
                                {emailError && <small className="p-error">Email already exist</small>}
                            </div>
                            <div className="field">
                                <span className="p-float-label">
                                    <Password id="password" name="password" value={formik.values.password} onChange={formik.handleChange} toggleMask
                                        className={classNames({ 'p-invalid': isFormFieldValid('password') })} header={passwordHeader} footer={passwordFooter} />
                                    <label htmlFor="password" className={classNames({ 'p-error': isFormFieldValid('password') })}>Password*</label>
                                </span>
                                {getFormErrorMessage('password')}
                            </div>
                            <div className="field">
                                <span className="p-float-label">
                                    <Dropdown id="profession" name="profession" value={formik.values.profession} onChange={formik.handleChange} options={professions} optionLabel="name" className={classNames({ 'p-error': isFormFieldValid('profession') })}/>
                                    <label htmlFor="profession" className={classNames({ 'p-error': isFormFieldValid('profession') })}>Profession*</label>
                                </span>
                                {getFormErrorMessage('profession')}
                            </div>
                            <div className="field-checkbox">
                                <Checkbox inputId="accept" name="accept" checked={formik.values.accept} onChange={formik.handleChange} className={classNames({ 'p-invalid': isFormFieldValid('accept') })} />
                                <label htmlFor="accept" className={classNames({ 'p-error': isFormFieldValid('accept') })}>I agree to the terms and conditions*</label>
                            </div>
                            <Button type="submit" label="Next step" className="mt-2" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
    );
}
