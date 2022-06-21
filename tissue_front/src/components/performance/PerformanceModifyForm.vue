<template>
    <div>
        <form @submit.prevent="onSubmit">
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
                                <label>S석가격</label>
                                <v-text-field outlined v-model="performPriceS" type="text" required></v-text-field>
                            </div>
                            <div>
                                <label>R석가격</label>
                                <v-text-field outlined v-model="performPriceR" type="text" required></v-text-field>
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

                            <!-- 썸네일 업로드 --> 
                            <!-- <div class="information-container">
                                <div class="information-title">썸네일 등록</div>
                                <div class="file-upload-wrapper">
                                    <div v-if="file == 0" class="file-upload-example-container">
                                        <div class="file-upload-example">
                                            <div class="file-notice-item file-upload-button">
                                                <div class="image-box">
                                                    <label for="file">등록</label>
                                                    <input type="file" id="file" ref="file" @change="thumbNailUpload" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div v-else class="file-preview-content-container">
                                        <div class="file-preview-container">
                                            <div class="file-preview-wrapper">
                                                <div class="file-close-button" @click="thumbNailDeleteButton">
                                                    x
                                                </div>
                                                <img :src="file.preview" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> -->

                            
                            <!-- 상세 이미지 업로드 --> 
                            <!-- <div class="information-container">
                                <div class="information-title">상세 이미지 등록</div>
                                <div class="picture-notice">
                                    <ul class="room-write-wrapper">
                                        <li>
                                            사진은 가로로 찍은 사진을 권장합니다. (가로 사이즈 최소 800px)
                                        </li>
                                        <li>사진 용량은 사진 한 장당 10MB 까지 등록이 가능합니다.</li>

                                    </ul>
                                </div>
                                <div class="file-upload-wrapper">
                                    <div v-if="!files.length" class="file-upload-example-container">
                                        <div class="file-upload-example">
                                            <div class="file-notice-item">
                                               이미지는 최소 1장 이상 등록하셔야 하며, 가로사진을 권장합니다.
                                            </div>
                                            <div class="file-notice-item file-notice-item-red">
                                                이미지는 최대 5장까지 등록 가능합니다.
                                            </div>
                                            <div class="file-notice-item file-upload-button">
                                                <div class="image-box">
                                                    <label for="multiFiles">등록</label>
                                                    <input type="file" id="multiFiles" ref="files" @change="imageUpload" multiple />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div v-else class="file-preview-content-container">
                                        <div class="file-preview-container">
                                            <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                                                <div class="file-close-button" @click="imageDeleteButton" :name="file.number">
                                                    x
                                                </div>
                                                <img :src="file.preview" />
                                            </div>
                                            <div class="file-preview-wrapper-upload">
                                                <div class="image-box">
                                                    <label for="addFiles">추가 사진 등록</label>
                                                    <input type="file" id="addFiles" ref="addFiles" @change="imageAddUpload" multiple />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> -->

                            <div class="information-container">
                                <div class="file-upload-wrapper">
                                    <label>ThumNail
                                        <input type="file" ref="file" id="file" @change="thumbNailUpload"/>
                                    </label> 
                                    <div class="file-preview-container">
                                            <div class="file-preview-wrapper">
                                                <div class="file-close-button" @click="thumbNailDeleteButton">
                                                    x
                                                </div>
                                                <img :src="file.preview" />
                                            </div>
                                    </div>
                                </div>
                            </div>

                            <div class="information-container">
                                <div class="file-upload-wrapper">
                                    <label>DetailImages
                                        <input type="file" ref="files" multiple @change="imageUpload"/>
                                    </label>
                                    <div class="file-preview-content-container">
                                        <div class="file-preview-container">
                                            <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                                                        <div class="file-close-button" @click="imageDeleteButton" :name="file.number">
                                                            x
                                                        </div>
                                                        <img :src="file.preview" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>




                            <!-- 지도 --> 
                            <div>
                                <label>지도첨부</label>
                                <add-map @selectMap="selectMap"/>
                            </div>
                        </form>
                    </v-col>
                </v-row>
                <br>
        </v-container>

        <div>
            <v-btn plain @click="[modifyFiles(),modifyMap()]">수정 완료</v-btn>
            <v-btn plain router-link :to="{ name: 'PerformanceReadPage',
                params: { performNo: performance.performNo.toString() } }">
                취소
            </v-btn>
        </div>
        </form>
    </div>
</template>

<script>
import AddMap from '@/components/map/AddMap.vue'
export default {
    name: 'PerformanceModifyForm',
    components: {
        AddMap
    },
    props: {
        performance: {
            type: Object,
            required: true
        }
    },
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
            files: [], // 다중이미지
            filesPreview: [],
            uploadImageIndex: 0,
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
    created () {
        this.performName = this.performance.performName
        this.performStart = this.performance.performStart
        this.performEnd = this.performance.performEnd
        this.performTime = this.performance.performTime
        this.performPriceR = this.performance.performPriceR
        this.performPriceS = this.performance.performPriceS
        this.performPriceVip = this.performance.performPriceVip
        this.performArea = this.performance.performArea
        this.performCategory = this.performance.performCategory
        this.performer = this.performance.performer
        // this.file = this.performance.performDetailImg
        // this.files = this.performance.performThumbnail
    },
    methods: {
        thumbNailUpload () {
            this.file = {
            file: this.$refs.file.files[0],
            preview: URL.createObjectURL(this.$refs.file.files[0])
            }
            console.log(this.file)
        },
       imageUpload () {
            // this.files = this.$refs.files.files
            // console.log(this.files)

            //  for (let i = 0; i < this.$refs.files.files.length; i++){
            //     this.files = [
            //         ...this.files,
            //         {
            //             file: this.$refs.files.files[i],
            //             preview: URL.createObjectURL(this.$refs.files.files[i])
                    
            //         }
            //     ]
            // }
            console.log('다중이미지 갯수: ', this.$refs.files.files.length)

            let num = -1;
            for (let i = 0; i < this.$refs.files.files.length; i++) {
                this.files = [
                    ...this.files,
                    {
                        file: this.$refs.files.files[i],
                        preview: URL.createObjectURL(this.$refs.files.files[i]),
                        number: i
                    }
                ]
                num = i;
            }
            this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장

            console.log(this.files)
        },
        imageDeleteButton(e) {
            const name = e.target.getAttribute('name');
            this.files = this.files.filter(data => data.number !== Number(name));
            console.log(this.files)
        },
        selectMap (name, address, x, y, phone, url) {
            this.map.name = name
            this.map.address = address
            this.map.x = x
            this.map.y = y
            this.map.phone = phone
            this.map.url = url
        },
        modifyFiles () {
            const { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
                    performPriceVip, performArea, performCategory, performGrade, performer} = this

            let formData = new FormData()

            formData.append('performName', performName)
            formData.append('performStart', performStart)
            formData.append('performEnd', performEnd)
            formData.append('performTime', performTime)
            formData.append('performPriceR', performPriceR)
            formData.append('performPriceS', performPriceS)
            formData.append('performPriceVip', performPriceVip)
            formData.append('performArea', performArea)
            formData.append('performCategory', performCategory)
            formData.append('performGrade', performGrade)
            formData.append('performer', performer)

            formData.append('file', document.getElementById('file').files[0])
        
            for (let idx = 0; idx < this.files.length; idx++) {
                formData.append('fileList', this.files[idx])
            }
            
            this.$emit('submit', { formData })
        },
        modifyMap() {
            setTimeout(() => {
                this.$emit('submit', this.map)
                // axios.post('http://localhost:7777/map/add', this.map )
                //     .then(()=> {
                //     alert('등록이 완료되었습니다!')
                //     this.$router.push(
                //         { name: 'PerformanceListPage' }
                //     )
                //     })
           }, 2000)
        }
    }
}
</script>
