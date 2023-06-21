<template>
    <el-form :model="news" label-width="120px">
        <el-form-item label="新闻标题">
            <el-input v-model="news.title" />
        </el-form-item>
        <el-form-item label="新闻封面">
            <el-upload action="#" list-type="picture-card" :auto-upload="false" :limit="1" :on-exceed="handleExceed">
                <el-icon>
                    <Plus />
                </el-icon>
                <template #file="{ file }">
                    <div>
                        <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                        <span class="el-upload-list__item-actions">
                            <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                                <el-icon><zoom-in /></el-icon>
                            </span>
                            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
                                <el-icon>
                                    <Download />
                                </el-icon>
                            </span>
                            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                                <el-icon>
                                    <Delete />
                                </el-icon>
                            </span>
                        </span>
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

const handleExceed: UploadProps['onExceed'] = (files) => {
    upload.value!.clearFiles()
    const file = files[0] as UploadRawFile
    file.uid = genFileId()
    upload.value!.handleStart(file)
}


// do not use same name with ref
const news = ref({
    title: '',
    img: '',
    context: '',
    author: ''
});


const onSubmit = () => {

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