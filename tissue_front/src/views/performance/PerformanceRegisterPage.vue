<template>
    <div class="mt-15">
       <v-container>
               <v-row>
                    <v-col>
                        <form>
                            <div>
                                <label>공연명</label>
                                <v-text-field outlined v-model="performName" type="text" required></v-text-field>
                            </div>
                            <div>
                                <label>공연시작일</label>
                                <v-text-field outlined v-model="performStart" type="Date" required></v-text-field>
                            </div>
                            <div>
                                <label>공연마감일</label>
                                <v-text-field outlined v-model="performEnd" type="Date" required></v-text-field>
                            </div>
                            <div>
                                <label>공연시간</label>
                                <v-text-field outlined v-model="performTime" type="Time" required></v-text-field>
                            </div>
                            <div>
                                <label>R석가격</label>
                                <v-text-field outlined v-model="performPriceR" type="text" required></v-text-field>
                            </div>
                            <div>
                                <label>S석가격</label>
                                <v-text-field outlined v-model="performPriceS" type="text" required></v-text-field>
                            </div>
                            <div>
                                <label>vip석가격</label>
                                <v-text-field outlined v-model="performPriceVip" type="text" required></v-text-field>
                            </div>
                            <div>
                                <label>지역</label>
                                <v-select v-model="performArea"
                                    :items="areaList" item-text="area" item-value="area"></v-select>
                            </div>
                            <div>
                                <label>공연카테고리</label>
                                <!-- <select class="form-control" v-model="performCategory">
                                    <option v-for="c in list" :key="c.index" :value="c.category">{{ c.category }}</option>
                                </select> -->
                                <v-select v-model="performCategory"
                                    :items="list" item-text="category" item-value="category"></v-select>
                            </div>
                            <div>
                                <label>등급</label>
                                <v-select v-model="performGrade"
                                    :items="ageList" item-text="age" item-value="age"></v-select>
                            </div>
                            <div>
                                <label>출연자</label>
                                <v-text-field outlined v-model="performer" type="text" required></v-text-field>
                            </div>
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
                            <div>
                                <label>지도첨부</label>
                                <add-map @selectMap="selectMap"/>
                            </div>

                        </form>
                    </v-col>
                </v-row>
                <br>
                <v-row>
                    <v-btn plain @click="[addMap(),submitFiles()]" value="Upload">
                        <v-icon>mdi-check-outline</v-icon>
                    </v-btn>
                </v-row>

        </v-container>
    </div>
</template>

<script>

import axios from 'axios'
import AddMap from '@/components/map/AddMap.vue'

export default {
  components: { AddMap },
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
                areaList: [
                    {area: '서울'},
                    {area: '경기/인천'},
                    {area: '대전/충청/강원'},
                    {area: '부산/대구/울산/경상'},
                    {area: '광주/전라'},
                    {area: '제주'},
                ],
                performCategory: '',
                list: [
                    {category: '뮤지컬'},
                    {category: '콘서트'},
                    {category: '연극'},
                    {category: '전시회'},
                ],
                performGrade: '',
                ageList: [
                    {age: '7세 이상 관람가'},
                    {age: '15세 이상 관람가'}
                ],
                performer: '',
                files: '', // 다중이미지
                file: '', // 썸네일
                response: '',
                 map: {
                    x: null,
                    y: null,
                    name: null,
                    address: null,
                    phone: null,
                    url: null,
                    performNo: null
                }
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
        selectMap (name, address, x, y, phone, url) {
            this.map.name = name
            this.map.address = address
            this.map.x = x
            this.map.y = y
            this.map.phone = phone
            this.map.url = url
        },
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
            .then ((res) => {
                // alert('공연이 등록되었습니다.')
                this.performNo = res.data.performNo
                this.map.performNo = res.data.performNo
                console.log(this.performNo)
                console.log(this.map.performNo)
                // this.$router.push({ name: 'PerformanceListPage' })
            })
            .catch (res => {
                alert('등록 실패: ' + res.message)
            })
        },
         addMap () {
            setTimeout(() => {
                axios.post('http://localhost:7777/map/add', this.map ).then(()=> {
                alert('등록이 완료되었습니다!')
                this.$router.push({ name: 'PerformanceListPage' })
                })
            }, 1000)
         }
  }
}
</script>   

<style scoped>
label {
    font-size: 5px;
}
</style>