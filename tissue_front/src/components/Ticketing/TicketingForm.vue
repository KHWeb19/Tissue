<template>
  <div>
    <v-container style="margin-top: 100px">
      <v-row>
        <v-col>
          <v-stepper v-model="e1">
            <v-stepper-header>
              <v-stepper-step
                :complete="e1 > 1"
                step="1"
                color="blue lighten-3"
              >
                좌석 선택
              </v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step
                :complete="e1 > 2"
                step="2"
                color="blue lighten-3"
              >
                할인 / 쿠폰
              </v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step
                :complete="e1 > 3"
                step="3"
                color="blue lighten-3"
              >
                정보 확인
              </v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step step="4" color="blue lighten-3">
                결제
              </v-stepper-step>
            </v-stepper-header>
            <div>변하는 정보</div>
            <v-stepper-items>
              <v-stepper-content step="1">
                <v-card
                  class="mb-12"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>

                <v-btn color="pink lighten-3" text @click="e1 = 2">
                  Continue
                </v-btn>

                <v-btn color="pink lighten-3" text to="/"> Cancel </v-btn>
              </v-stepper-content>

              <v-stepper-content step="2">
                <v-card
                  class="mb-12"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>

                <v-btn color="pink lighten-3" text @click="e1 = 3">
                  Continue
                </v-btn>

                <v-btn color="pink lighten-3" text @click="e1 = 1">
                  Cancel
                </v-btn>
              </v-stepper-content>

              <v-stepper-content step="3">
                <v-card
                  class="mb-12"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>

                <v-btn color="pink lighten-3" text @click="e1 = 4">
                  Continue
                </v-btn>

                <v-btn color="pink lighten-3" text @click="e1 = 2">
                  Cancel
                </v-btn>
              </v-stepper-content>

              <v-stepper-content step="4">
                <v-card
                  class="mb-12"
                  color="grey lighten-1"
                  height="200px"
                ></v-card>

                <v-btn color="pink lighten-3" text @click="e1 = 1">
                  Continue
                </v-btn>

                <v-btn color="pink lighten-3" text @click="e1 = 3">
                  Cancel
                </v-btn>
              </v-stepper-content>
            </v-stepper-items>
          </v-stepper>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "TicketingForm",

  data() {
    return {
      e1: 1,
    };
  },
};
</script>
