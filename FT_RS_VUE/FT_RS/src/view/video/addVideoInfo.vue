<template>
    <el-dialog title="编辑内容" width="50%" :append-to-body='true'>


        <!-- <el-form :model="video" label-width="120px" :rules="rules"> -->
        <el-form :model="video" label-width="120px">
            <el-form-item label="电影名">
                <el-input v-model="video.name" />
            </el-form-item>
            <el-form-item label="导演">
                <el-input v-model="video.director" />
            </el-form-item>

            <el-form-item label="编剧">
                <el-input v-model="video.screenwriter" />
            </el-form-item>

            <el-form-item label="主演">
                <el-input v-model="video.starring" />
            </el-form-item>
            <el-form-item label="形式">
                <el-select v-model="video.type" placeholder="选择媒体形式">
                    <el-option v-for="(type, index) in typeData" :key="index" :label="type.type" :value="type.type" />
                </el-select>
            </el-form-item>
            <el-form-item label="上映时间">
                <el-col :span="11">
                    <el-date-picker v-model="video.releasedata" type="date" placeholder="选择日期" style="width: 100%" />
                </el-col>
                <el-col :span="13">
                    <el-form-item label="影片时长">
                        <el-input v-model="video.length" />
                    </el-form-item>
                </el-col>
            </el-form-item>

            <el-form-item label="类型">
                <el-checkbox-group v-model="categorys">
                    <el-checkbox v-for="(category, index) in categoryData" :key="index" :label="category.id" border>{{
                        category.name }}</el-checkbox>
                </el-checkbox-group>
            </el-form-item>



            <el-form-item label="海报封面">

                <el-upload ref="upload" class="upload-demo" action="#" :limit="1" :on-exceed="handleExceed"
                    :auto-upload="false" :file-list="fileList" :on-change="handleChange">
                    <template #trigger>
                        <el-button type="primary">选择文件</el-button>

                    </template>

                    <el-button type="success" @click="submitUpload">
                        上传
                    </el-button>
                    <el-button type="default" @click="handlePictureCardPreview">
                        预览
                    </el-button>
                    <template #tip>
                        <div class="el-upload__tip text-red">
                            limit 1 file, new file will cover the old file
                        </div>
                    </template>
                </el-upload>
                <el-dialog v-model="dialogVisible" :width="'550px'">
                    <img class="p_img" :src="dialogImageUrl" alt="Preview Image" />
                </el-dialog>

            </el-form-item>

            <el-form-item label="简介">
                <el-input v-model="video.introduction" type="textarea" />
            </el-form-item>

        </el-form>
        <template #footer>
            <el-button type="primary" @click="onSubmit">添加</el-button>
        </template>
    </el-dialog>
</template>
  
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'
import API from '../../plugins/axiosInstance';
import type { UploadFile } from 'element-plus'


//图片上传设置
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const disabled = ref(false)



//图片预览
const handlePictureCardPreview = (file: UploadFile) => {
    dialogImageUrl.value = file.url!
    dialogVisible.value = true
}


//图片替换
import { genFileId, ElMessage } from 'element-plus'
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'

const upload = ref<UploadInstance>()

const handleExceed: UploadProps['onExceed'] = (files) => {
    upload.value!.clearFiles()
    const file = files[0] as UploadRawFile
    file.uid = genFileId()
    upload.value!.handleStart(file)
}


import { onMounted } from 'vue';

const formData = new FormData()
const currentFile = ref()

// 获取用户选择的文件对象
const handleChange = (file: any) => {
    currentFile.value = file
}


//文件上传


const submitUpload = () => {
    formData.append("file", currentFile.value.raw)
    API({
        url: '/upload/videoImg',
        method: 'post',
        headers: {
            'Content-Type': 'multipart/form-data' // 指定请求的数据格式为 multipart/form-data
        },
        data: formData
    })
        .then(res => {
            video.img = res.data.url // 获取上传成功后返回的文件链接地址
            alert(res.data.url)
        })
        .catch(error => {
            // 处理上传失败的错误
        })
}






//下拉菜单设置
const typeData = ref()
const getType = function () {
    API({
        url: '/type/findAll',
        method: 'get'
    }).then((res) => {
        typeData.value = res.data.data;
    });
}
getType()


//复选框设置
const categoryData = ref()
const getCategory = function () {
    API({
        url: '/category/findAll',
        method: 'get'
    }).then((res) => {
        categoryData.value = res.data.data;
    });
}
getCategory()

// do not use same name with ref
const video = reactive({
    name: '',
    countryRegion: '',
    director: '',
    screenwriter: '',
    starring: '',
    type: '',
    language: '',
    releasedata: '',
    length: '',
    img: '',
    introduction: ''
});
const categorys = ref()

const id = ref()
const videoId = ref()
//添加
const add = function () {
    API({
        url: '/video/add',
        method: 'post',
        data: video
    }).then((res) => {
        id.value = res.data.data.id;
        categoryAdd(id)
    });
    return {
        id
    }

}
const categoryAdd = function (id) {
    const params = {
        categorys: categorys.value,
        videoId: id.value
    }
    API({
        url: '/videoCategory/add',
        method: 'post',
        data: categorys
        // headers: {
        //     'Content-Type': 'application/json'
        // },
    }).then((res) => {

    });

}
getCategory()


// 表单验证规则
// import type { FormInstance, FormRules } from 'element-plus'
// const rules = reactive<FormRules>({
//     name: [
//         { required: true, message: '请输入电影名', trigger: 'blur' },
//     ],
//     region: [
//         {
//             required: true,
//             message: 'Please select Activity zone',
//             trigger: 'change',
//         },
//     ],
//     count: [
//         {
//             required: true,
//             message: 'Please select Activity count',
//             trigger: 'change',
//         },
//     ],
//     date1: [
//         {
//             type: 'date',
//             required: true,
//             message: 'Please pick a date',
//             trigger: 'change',
//         },
//     ],
//     date2: [
//         {
//             type: 'date',
//             required: true,
//             message: 'Please pick a time',
//             trigger: 'change',
//         },
//     ],
//     type: [
//         {
//             type: 'array',
//             required: true,
//             message: 'Please select at least one activity type',
//             trigger: 'change',
//         },
//     ],
//     resource: [
//         {
//             required: true,
//             message: 'Please select activity resource',
//             trigger: 'change',
//         },
//     ],
//     desc: [
//         { required: true, message: 'Please input activity form', trigger: 'blur' },
//     ],
// })

const onSubmit = () => {
    add()
}
</script>

<style scoped>
.p_img {
    max-width: 500px;
    max-height: auto;
}
</style>