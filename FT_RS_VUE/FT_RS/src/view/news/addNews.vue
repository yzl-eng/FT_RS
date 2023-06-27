<template>
    <el-dialog title="编辑内容" width="50%" :append-to-body='true'>
        <el-form :model="news" label-width="120px" ref="edit_form">
            <el-form-item label="新闻标题">
                <el-input v-model="news.title" />
            </el-form-item>
            <el-form-item label="新闻封面">
                <el-upload ref="upload" class="upload-demo" action="#" :limit="1" :on-exceed="handleExceed"
                    :auto-upload="false" :on-change="handleChange">
                    <template #trigger>
                        <el-button type="primary">选择文件</el-button>

                    </template>
                    
                    <el-button type="default" @click="handlePictureCardPreview">
                        预览
                    </el-button>

                    <el-button type="success" @click="submitUpload">
                        上传
                    </el-button>
                    
                    <template #tip>
                        <div class="el-upload__tip text-red">
                            限制一张图片，新图片会替换旧图片
                        </div>
                    </template>
                </el-upload>
                <el-dialog v-model="dialogVisible" :width="'550px'">
                    <img class="p_img" :src="dialogImageUrl" alt="Preview Image" />
                </el-dialog>
            </el-form-item>
            <el-form-item label="作者">
                <el-input v-model="news.author" />
            </el-form-item>
            <el-form-item label="内容">
                <el-input v-model="news.context" type="textarea" />
            </el-form-item>
            <el-form-item><el-button type="primary" @click="onSubmit">添加</el-button>
                <el-button>取消</el-button></el-form-item>
        </el-form>
    </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'
import API from '../../plugins/axiosInstance';
import type { UploadFile } from 'element-plus'

const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const disabled = ref(false)

const handleRemove = (file: UploadFile) => {
    console.log(file)
}

const handlePictureCardPreview = (file: UploadFile) => {
    dialogImageUrl.value = file.url!
    dialogVisible.value = true
}

const handleDownload = (file: UploadFile) => {
    console.log(file)
}

import { genFileId } from 'element-plus'
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'

const upload = ref<UploadInstance>()

// do not use same name with ref
const news = ref({
    title: '未命名标题',
    img: 'src/assets/logo.png',
    context: '无',
    author: '无'
});

//图片替换
import { ElMessage} from 'element-plus'

const handleExceed: UploadProps['onExceed'] = (files) => {
    upload.value!.clearFiles()
    const file = files[0] as UploadRawFile
    file.uid = genFileId()
    upload.value!.handleStart(file)
}


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
        url: '/upload/newsImg',
        method: 'post',
        headers: {
            'Content-Type': 'multipart/form-data' // 指定请求的数据格式为 multipart/form-data
        },
        data: formData
    }).then(res => {
        news.value.img = res.data // 获取上传成功后返回的文件链接地址
        alert("上传成功")
        

    })
        .catch(error => {
            // 处理上传失败的错误
            alert("错误")
        })
}
import { nextTick} from 'vue';
const edit_form=ref(null)
const onSubmit =  () => {
    API({
        url: '/news/add',
        method: 'post',
        data: news.value
    }).then((res) => {
        
        alert("添加成功")
    });
}
</script>

<style scoped>
.p_img {
    max-width: 500px;
    max-height: auto;
}
</style>