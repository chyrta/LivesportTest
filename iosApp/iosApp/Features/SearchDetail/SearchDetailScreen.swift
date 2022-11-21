//
//  SearchDetailScreen.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 20.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import LivesportKit
import Kingfisher

struct SearchDetailScreen: View {
    var item: SearchResultItemEntity
    
    var body: some View {
        VStack {
            KFImage(URL(string: item.image!)!)
                .resizable()
                .frame(width: 100, height: 100)
                .clipShape(Circle())
                .onAppear()
            Text(item.name)
        }
    }
}
