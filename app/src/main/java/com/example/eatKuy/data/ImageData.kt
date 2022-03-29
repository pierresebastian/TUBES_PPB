package com.example.eatKuy.data

object ImageData {
    private val imgData = arrayOf(
        "https://ak6.picdn.net/shutterstock/videos/1036694126/thumb/1.jpg",
        "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fcdn-image.realsimple.com%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Frs_medium_image%2Fpublic%2Fpotato-spinach-frittata.jpg%3Fitok%3D367_2xDc%261516916417",
        "https://cdn3.tmbi.com/toh/GoogleImagesPostCard/exps38530_TH143344C06_23_1b.jpg",
        "https://diethood.com/wp-content/uploads/2019/09/Roasted-Pork-Loin-4.jpg",
        "https://www.thespruceeats.com/thmb/IJ4CaeCFLswZzWuT86NyI7KDP-g=/1333x1000/smart/filters:no_upscale()/colcannon-1500-58a714c33df78c345b75859b.jpg",
        "https://www.smalltownwoman.com/wp-content/uploads/2014/11/Chocolate-Cherry-Cookies-DSC_0067.jpg",
        "https://www.cookingforkeeps.com/wp-content/uploads/2019/09/Creamy-Tomato-Pasta-Recipe-1.jpg",
        "https://www.thecookierookie.com/wp-content/uploads/2018/11/pear-ginger-spiced-cider-2-of-10.jpg",
        "https://i.pinimg.com/originals/a7/41/ce/a741ce6878e1e6d122550444e85442ee.jpg",
        "https://www.foodiecrush.com/wp-content/uploads/2019/06/Greek-Salad-with-Grilled-Chicken-foodiecrush.com-005.jpg")

    val listImgData: ArrayList<String>
        get() {
            val listImg = ArrayList<String>()
            for(element in imgData){
                listImg.add(element)
            }
            return listImg
        }
}