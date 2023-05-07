import React, { useState } from 'react';
import { useFormik } from 'formik';
import { classNames } from 'primereact/utils';
import { Button } from 'primereact/button';
import { Steps } from 'primereact/steps';
import { InputText } from 'primereact/inputtext';
import { Message } from 'primereact/message';
import axios from 'axios';
import './Step3.css';

export function Step3(props){
    const items = [
        {label: 'Register'},
        {label: 'User details'},
        {label: 'Validation'},
        {label: 'Finalization'}
    ];
    const [error,setError] = useState(false);
    const formik = useFormik({
        initialValues: {
            value1: "",
            value2: "",
            value3: "",
            value4: ""
        },validate: (data) => {
            let errors = {};

            if (!data.value1 ) {
                errors.value1 = 'Missing field ';
            }
            if (!data.value2 ) {
                errors.value2 = 'Missing field';
            }
            if (!data.value3 ) {
                errors.value3 = 'Missing field';
            }
            if (!data.value4 ) {
                errors.value4 = 'Missing field';
            }
            if (!data.value4 || !data.value3 || !data.value2 || !data.value1) {
                errors.field = 'Missing field';
            }
            return errors;
        },
        onSubmit: (data) => {
            const code=data.value1+data.value2+data.value3+data.value4;
            const id=props.id;
            axios.post(`${process.env.REACT_APP_HOST}/login/signin`,null,{ params: {
             code : code,
             id : id,
            }
            })
              .then((Response)=> {
                if(Response.data){
                    props.Onsubmit3(true);
                }else {
                  setError(true);
                  formik.resetForm();
                }
              });
    }
    });
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const isFormValid = (value1,value2,value3,value4) => !!(formik.touched[value1] && formik.touched[value2] && formik.touched[value3] && formik.touched[value4] && formik.errors.field);
    const getFormErrorMessage = (value1,value2,value3,value4) => {
        return isFormValid(value1,value2,value3,value4) && <small className="p-error" >{formik.errors.field}</small>;
    };
    return(
        <div>
            <Steps model={items} className='Steps' activeIndex={2}/>
            <div className="form-demo">
                <div className="flex justify-content-center">
                    <div className="card">
                        <form onSubmit={formik.handleSubmit} className="p-fluid">
                            <div className='values'>
                                <span className='value'>
                                    <InputText name="value1" value={formik.values.value1} onKeyPress={(e) => !/[0-9]/.test(e.key) && e.preventDefault()} autoFocus maxLength={1} onChange={formik.handleChange} className={classNames({ 'p-invalid': isFormFieldValid('value1') })} />
                                </span>
                                <span className='value'>
                                    <InputText name="value2" value={formik.values.value2} onKeyPress={(e) => !/[0-9]/.test(e.key) && e.preventDefault()} maxLength={1} onChange={formik.handleChange} className={classNames({ 'p-invalid': isFormFieldValid('value2') })} />
                                </span>
                                <span className='value'>
                                    <InputText name="value3" value={formik.values.value3} onKeyPress={(e) => !/[0-9]/.test(e.key) && e.preventDefault()} maxLength={1} onChange={formik.handleChange} className={classNames({ 'p-invalid': isFormFieldValid('value3') })} />
                                </span>
                                <span className='value'>
                                    <InputText name="value4" value={formik.values.value4} onKeyPress={(e) => !/[0-9]/.test(e.key) && e.preventDefault()} maxLength={1} onChange={formik.handleChange} className={classNames({ 'p-invalid': isFormFieldValid('value4') })} />
                                </span>
                            </div>
                            <div className='error'>
                                {getFormErrorMessage('value1','value2','value3','value4')}
                            </div>
                            {error && <Message severity="error" text="Wrong code!!Please verify your email"></Message>}
                            <div className='submit'>
                                <Button type="submit" label="Submit" className="mt-2" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}