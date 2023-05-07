import React, { useRef } from 'react';
import { useFormik } from 'formik';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { classNames } from 'primereact/utils';
import { useNavigate} from 'react-router-dom';
import { Toast } from 'primereact/toast'
import axios from 'axios';
import './Login.css';
export function Login (props) {
    let navigate = useNavigate();
    const formik = useFormik({
        initialValues: {
            name: '',
            password: '',
        },
        validate: (data) => {
            let errors = {};

            if (!data.name) {
                errors.name = 'Name is required.';
            }

            if (!data.password) {
                errors.password = 'Password is required.';
            }

            return errors;
        },
        onSubmit: (data) => {
            const username=data.name;
            const password=data.password;
            axios.post(`${process.env.REACT_APP_HOST}/user/login`,null,{
                params: {
                    username : username,
                    password : password,
                }
            })
            .then((Response)=>{
                const accessToken=Response.data.accessToken;
                const refreshToken=Response.data.refreshToken;
                axios.get(`${process.env.REACT_APP_HOST}/user/get/username=${username}`).then((Response)=>{
                props.Onlogin(accessToken, refreshToken,Response.data);
                navigate("/");
                })
            })
            .catch((error)=>{
                if(error.response.status===403){
                    formik.resetForm();
                    showError();
                }
            })
            
        }
    });
    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };
    const toast=useRef(null);
    const showError = () => {
        toast.current.show({severity:'error', summary: 'Incorrect information', detail:'please verify your username or password', life: 3000});
    }
    return (
        <div>
            <div className="form-demo">
                <div className="flex justify-content-center">
                    <div className="card">
                        <form onSubmit={formik.handleSubmit} className="p-fluid">
                            <div className="field">
                                <span className="p-float-label">
                                    <InputText id="name" name="name" value={formik.values.name} onChange={formik.handleChange} autoFocus className={classNames({ 'p-invalid': isFormFieldValid('name') })} />
                                    <label htmlFor="name" className={classNames({ 'p-error': isFormFieldValid('name') })}>User name</label>
                                </span>
                                {getFormErrorMessage('name')}
                            </div>
                            <div className="field">
                                <span className="p-float-label">
                                    <Password id="password" name="password" value={formik.values.password} onChange={formik.handleChange} feedback={false} toggleMask
                                        className={classNames({ 'p-invalid': isFormFieldValid('password') })} />
                                    <label htmlFor="password" className={classNames({ 'p-error': isFormFieldValid('password') })}>Password</label>
                                </span>
                                {getFormErrorMessage('password')}
                            </div>
                            <Toast ref={toast}/>
                            <Button type="submit" label="Login" className="mt-2" />
                        </form>
                    </div>
                </div>
            </div>
        </div>   
    );
}
