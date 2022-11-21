//
//  SearchListViewRow.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 19.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Kingfisher
import LivesportKit

struct SearchListRowView: View {

    var item: SearchResultItemEntity

    var body: some View {
        HStack {
            KFImage(URL(string: item.image!))
                .resizable()
                .frame(width: 48, height: 48)
                .clipShape(Circle())
                .onAppear()
            Text(item.name)
        }
    }
}
