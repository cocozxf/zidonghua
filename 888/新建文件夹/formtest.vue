<template>
    <div>
        <h3>elment表单,rules校验</h3>
        <!--model数据模型-->
        <el-form :model="ruleForm" :rules="rules" ref="loginForm">
            <el-form-item label="用户名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="pwd">
                <el-input v-model="ruleForm.pwd"></el-input>
            </el-form-item>
            <el-form-item >
                <el-button type="primary" @click="submitForm()">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm:{
                    name:"",
                    pwd:""
                },
                rules:{
                    name:[{required: true,message:"请输入名称"},
                    { min:6,max:10,message:"请输入6~10位用户名"}
                    ],
                    pwd:[{required:true,message:"请输入密码"}] 
                }
            }
        },
        methods: {
            submitForm() {
                this.$refs.loginForm.validate(valid=>{
                    if(valid){
                        alert("提交登录")
                    }else{
                        console.log("校验失败");
                        return false;
                    }
                })
            }
        },
    }
</script>

<style lang="scss" scoped>

</style>