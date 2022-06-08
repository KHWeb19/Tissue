<template>
    <div align="center">
       <v-container>
            <h4 style="margin-top:1%;"><strong>공연 업로드 </strong></h4>
                <v-text-field type="text" required height="5vh" style="margin-top:10px;" v-model="performName" label="공연명"></v-text-field>
                <v-text-field type="Date" v-model="performStart" label="공연시작일"></v-text-field>
                <v-text-field type="Date" v-model="performEnd" label="공연마감일"></v-text-field>
                <v-text-field type="Time" v-model="performTime" label="공연시간"></v-text-field>
                <v-text-field type="Number" v-model="performPriceR" label="R석가격"></v-text-field>
                <v-text-field type="Number" v-model="performPriceS" label="S석가격"></v-text-field>
                <v-text-field type="Number" v-model="performPriceVip" label="vip석가격"></v-text-field>
                <v-text-field type="text" v-model="performArea" label="지역"></v-text-field>
                <v-text-field type="text" v-model="performCategory" label="공연카테고리"></v-text-field>
                <v-text-field type="text" v-model="performGrade" label="등급"></v-text-field>
                <v-text-field type="text" v-model="performer" label="출연자"></v-text-field>
                
                <kakao-map/>

                <div class="file-upload-list">
                    <label>Thumbnail
                        <input type="file" ref="file" id="file" @change="handleThumbNailUpload()"/>
                    </label>
                    <div>
                        <img :src=file.preview class="preview"/>
                    </div>
                    <!-- <div class="file-upload-list__item__btn-remove" @click="handleRemove1()">
                        삭제
                    </div> -->
                </div>
                
                <div class="file-upload-list">
                    <label>DetailFiles
                        <input type="file" ref="files" multiple @change="handleFileUpload()"/>
                    </label>
                    <div v-for="(file, index) in files" :key="index" style="text-align:center">
                        <img :src=file.preview class="preview"/>
                    </div>
                    <!-- <div class="file-upload-list__item__btn-remove" @click="handleRemove2(index)">
                        삭제
                    </div> -->
                </div>
                <button @click="submitFiles()" value="Upload">파일 업로드</button>
        </v-container>
    </div>
</template>

<script>

import axios from 'axios'
import KakaoMap from '@/components/map/KakaoMap.vue'
export default {
  components: { KakaoMap },
  name: 'PerformanceRegisterPage',
  data () {
        return {
                performName: '',
                performStart: '',
                performEnd: '',
                performTime: '',
                performPriceR: '',
                performPriceS: '',
                performPriceVip: '',
                performArea: '',
                performCategory: '',
                performGrade: '',
                performer: '',
                files: '', // 다중이미지
                file: '', // 썸네일
                response: '' 
        }
    },
  methods: {
      handleThumbNailUpload () {
        this.file = this.$refs.file.files
        console.log(this.file)

        this.file = {
            file: this.$refs.file.files[0],
            preview: URL.createObjectURL(this.$refs.file.files[0])
        }

      },
       handleFileUpload () {
            this.files = this.$refs.files.files
            console.log(this.files)

             for (let i = 0; i < this.$refs.files.files.length; i++){
                this.files = [
                    ...this.files,
                    {
                        file: this.$refs.files.files[i],
                        preview: URL.createObjectURL(this.$refs.files.files[i])
                    
                    }
                ]
            }
        },
        // handleRemove1 () {
        //     this.fileList.splice(index, 1)
        // },
        // handleRemove2(index) {
        //     this.fileList.splice(index, 1)
        // },
        submitFiles () {
            let formData = new FormData()

            formData.append('performName', this.performName)
            formData.append('performStart', this.performStart)
            formData.append('performEnd', this.performEnd)
            formData.append('performTime', this.performTime)
            formData.append('performPriceR', this.performPriceR)
            formData.append('performPriceS', this.performPriceS)
            formData.append('performPriceVip', this.performPriceVip)
            formData.append('performArea', this.performArea)
            formData.append('performCategory', this.performCategory)
            formData.append('performGrade', this.performGrade)
            formData.append('performer', this.performer)

            formData.append('file', document.getElementById('file').files[0])
        
            for (let idx = 0; idx < this.files.length; idx++) {
                formData.append('fileList', this.files[idx])
            }
            
            axios.post('http://localhost:7777/performance/register', formData, {
                headers: {
                    // 'Access-Control-Allow-Origin': '*',
                    // 'Access-Control-Allow-Headers': '*',
                    // 'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
                    // 'Access-Control-Allow-Credentials': true,
                    // 'Access-Control-Max-Age': '3600',
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then (res => {
                alert('처리 결과: ' + res.data)
            })
            .catch (res => {
                alert('처리 결과: ' + res.message)
            })
          
            // const { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
            //     performPriceVip, performArea, performCategory, performGrade, performer, file } = this
            // this.$emit('submit', { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
            // performPriceVip, performArea, performCategory, performGrade, performer, file })
        }
   
  }
}
</script>   